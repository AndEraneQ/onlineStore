package pl.onlineStore.ItemsInShop;
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
    public void addQuantityOfProductsToDatabase(String productName, int howMuch){
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "UPDATE itemsInShop SET quantity = quantity + ? WHERE name = ?";
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
                addQuantityOfProductsToDatabase(item.getName(), item.getQuantity());
            }
            user.setShoppingList(null);
        }
    }
    public ArrayList collectListOfItemsFromCategory(String category){
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
}
