package pl.onlineStore.ItemsInShop;

import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CollectDataForItems implements DataToConnectToSql {
    private Scanner scanner = new Scanner(System.in);
    public String collectCategory() {
        ManageItems manageItems = new ManageItems();
        String categoryOfItemWrittenByUser = null;
        boolean categoryIsValid = false;
        ArrayList<String> listOfCategory = manageItems.collectListOfCategory();
        while (!categoryIsValid)
            System.out.println("Type category from the list bellow:");
            for (String category : listOfCategory) {
                System.out.print(category + " ");
            }
            System.out.println();
            categoryOfItemWrittenByUser = scanner.nextLine();
            for (String category : listOfCategory) {
                if (category.equals(categoryOfItemWrittenByUser)) {
                    categoryIsValid = true;
                }
            }
        return categoryOfItemWrittenByUser;
    }
    public String collectName() {
        while(true){
            System.out.println("Type the name of the product:");
            String nameOfProduct = scanner.nextLine();
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
            price = scanner.nextLine();
            try {
                if (price.startsWith("0") || !price.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                    System.out.println("Wrong type of number. Please type it correctly");
                } else {
                    double priceInDouble = Double.parseDouble(price);
                    return priceInDouble;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
            }
        }
    }
    public int collectHowMuch(){
        int number =0;
        while(true){
            System.out.println("Type how much items will be in store");
            String howMuch = scanner.nextLine();
            try {
                number = Integer.parseInt(howMuch);
                if (number < 0) {
                    System.out.println("Number can't be negative! try again");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("You need to write an integer");
            }
        }
    }
}
