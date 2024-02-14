package pl.onlineStore.ItemsInShop;

import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CollectDataForItems implements DataToConnectToSql {
    private Scanner scanner = new Scanner(System.in);
    public String collectCategory(){
        ManageItems manageItems = new ManageItems();
        ArrayList<String> listOfCategory = new ArrayList<>();
        listOfCategory = manageItems.collectListOfCategory();
        boolean categoryIsCorrect = false;
        String categoryOfItemWrittenByUser="";
        do {
            System.out.println("Type category from category bellow");
            for (String category : listOfCategory) {
                System.out.print(category + " ");
            }
            System.out.println();
            categoryOfItemWrittenByUser = scanner.nextLine();
            for(String category : listOfCategory){
                if(category.equals(categoryOfItemWrittenByUser)){
                    categoryIsCorrect = true;
                }
            }
        } while(!categoryIsCorrect);
        return categoryOfItemWrittenByUser;
    }
    public String collectName() {
        boolean nameIsCorrect=false;
        do {
            System.out.println("Type name of item");
            String nameOfItem = scanner.nextLine();
            try {
                Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
                String sql = "SELECT name FROM itemsInshop WHERE name = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nameOfItem);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    nameIsCorrect=true;
                    return nameOfItem;
                } else {
                    System.out.println("Item exist, try again");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } while(!nameIsCorrect);
        return "";
    }
    public double collectPrice(){
        boolean moneyIsCorrect = false;
        String price = null;
        while (!moneyIsCorrect) {
            System.out.println("Type price for item: ");
            price = scanner.nextLine();
            try {
                if (price.startsWith("0") || !price.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                    System.out.println("Wrong type of number. Please type it correctly");
                } else {
                    moneyIsCorrect=true;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
            }
        }
        double priceInDouble = Double.parseDouble(price);
        return priceInDouble;
    }
    public int collectHowMuch(){
        boolean numberIsCorrect=false;
        int number =0;
        do {
            System.out.println("Type how much items will be in store");
            String howMuch = scanner.nextLine();
            try {
                number = Integer.parseInt(howMuch);
                if (number < 0) {
                    System.out.println("Number can't be negative! try again");
                } else {
                    numberIsCorrect=true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You need to write an integer");
            }
        } while(!numberIsCorrect);
        return number;
    }
}
