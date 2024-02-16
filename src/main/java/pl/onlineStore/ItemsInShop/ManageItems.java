package pl.onlineStore.ItemsInShop;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.users.User;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.UserDataSingleton;

import java.sql.*;
import java.util.ArrayList;

public class ManageItems implements DataToConnectToSql {
    private User user = UserDataSingleton.getInstance().getUser();
    public ArrayList collectListOfCategory(){
        ArrayList<String> categoryOfItems = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT category FROM shoppingCategory";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String category = resultSet.getString("category");
                categoryOfItems.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryOfItems;
    }
    public void addOrDeleteQuantityOfProductsToDatabase(String productName, int howMuch, Character operation){
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "UPDATE itemsInShop SET quantity = quantity " + operation + " ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,howMuch);
            statement.setString(2,productName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearShoppingList(){
        if(user.getShoppingList()!=null){
            for(Item item : user.getShoppingList()){
                addOrDeleteQuantityOfProductsToDatabase(item.getName(), item.getQuantity(), '+');
            }
            user.setShoppingList(null);
        }
    }
    public ArrayList collectListOfItemsNamesFromCategory(String category){
        ArrayList<String> namesOfItems = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT name FROM itemsInShop WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,category);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String nameOfItem = resultSet.getString("name");
                namesOfItems.add(nameOfItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return namesOfItems;
    }
    public ArrayList collectListOfItemsFromOneCategory(String categoryOfProduct){
        ArrayList<Item> listOfItemsInCategory = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT * FROM itemsInShop WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,categoryOfProduct);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Item item = new Item();
                item.setCategory(categoryOfProduct);
                item.setName(resultSet.getString("name"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getDouble("price"));
                listOfItemsInCategory.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfItemsInCategory;
    }
    public Item collectItemFromList(ArrayList nameOfTheList){
        Choice choice = new Choice();
        String nameOfItem;
        while (true) {
            System.out.println("Choose a product from list bellow");
            for (Item item : user.getShoppingList()) {
                System.out.print(item.getName() + " ");
            }
            nameOfItem = choice.getStringChoice();
            for (Item item : user.getShoppingList()) {
                if (nameOfItem.equals(item.getName())) {
                    return item;
                }
            }
        }
    }
}
