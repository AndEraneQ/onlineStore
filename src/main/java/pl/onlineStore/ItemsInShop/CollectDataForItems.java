package pl.onlineStore.ItemsInShop;

import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.choices.Choice;

import java.sql.*;
import java.util.ArrayList;

public class CollectDataForItems implements DataToConnectToSql {
    Choice choice = new Choice();
    public String collectCategory() {
        ManageItems manageItems = new ManageItems();
        String categoryOfItemWrittenByUser = null;
        boolean categoryIsValid = false;
        ArrayList<String> listOfCategory = manageItems.collectListOfCategory();
        while (!categoryIsValid) {
            System.out.println("Type category from the list bellow:");
            for (String category : listOfCategory) {
                System.out.print(category + " ");
            }
            System.out.println();
            categoryOfItemWrittenByUser = choice.getStringChoice();
            for (String category : listOfCategory) {
                if (category.equals(categoryOfItemWrittenByUser)) {
                    categoryIsValid = true;
                }
            }
        }
        return categoryOfItemWrittenByUser;
    }
    public String collectName() {
        while(true){
            System.out.println("Type the name of the product:");
            String nameOfProduct = choice.getStringChoice();
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
                String sql = "SELECT name FROM itemsInshop WHERE name = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nameOfProduct);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return nameOfProduct;
                } else {
                    System.out.println("Item exist, try again");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public double collectPrice(){
        String price = null;
        while (true) {
            System.out.println("Type the price for the item: ");
            price = choice.getStringChoice();
                if (price.startsWith("0") || !price.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                    System.out.println("Wrong type of number. Please type it correctly");
                } else {
                    double priceInDouble = Double.parseDouble(price);
                    return priceInDouble;
                }
        }
    }
    public int collectHowMuch(){
        int number;
        while(true){
            System.out.println("Type how much items will be in store");
            number = choice.getIntChoice();
            if (number < 0) {
                    System.out.println("Number can't be negative! try again");
            } else {
                return number;
            }
        }
    }
    public Item collectAllDataForItem(){
        Item item = new Item();
        item.setName(collectName());
        item.setCategory(collectCategory());
        item.setPrice(collectPrice());
        item.setQuantity(collectHowMuch());
        return item;
    }
}
