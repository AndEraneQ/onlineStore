package pl.onlineStore.AdminActions;

import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditItemsDataAction implements DataToConnectToSql {
    private CollectDataForItems collectDataForItems = new CollectDataForItems();
    private Scanner scanner = new Scanner(System.in);
    private String editingCategory;
    private String editingData;
    private void menuOfEditItems(){
        System.out.println("Choose what you want to change: ");
        System.out.println("1 - edit name");
        System.out.println("2 - edit category");
        System.out.println("3 - edit price");
        System.out.println("4 - edit quantity");
        System.out.println("5 - back");
    }
    public void editItemsRun(){
        int choice=0;
        boolean choiceIsCorrect;
        do {
            choiceIsCorrect=true;
            System.out.println("Select category: ");
            editingCategory = collectDataForItems.collectCategory();
            ArrayList<String> listOfItemsInCategory= new ArrayList<>();
            listOfItemsInCategory = collectListOfItemsFromCategory(editingCategory);
            boolean editingDataIsCorrect = false;
            do {
                System.out.println("Select item from list: ");
                if(listOfItemsInCategory.size()==0){
                    System.out.println("There is 0 items in this category");
                    return;
                }
                for (String name : listOfItemsInCategory) {
                    System.out.print(name + " ");
                }
                System.out.println();
                editingData = scanner.nextLine();
                for(String name : listOfItemsInCategory){
                    if(name.equals(editingData)){
                        editingDataIsCorrect=true;
                    }
                }
            } while(!editingDataIsCorrect);
            menuOfEditItems();
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        editName();
                        break;
                    case 2:
                        editCategory();
                        break;
                    case 3:
                        editPrice();
                        break;
                    case 4:
                        editQuantity();
                        break;
                    case 5:
                        System.out.println("Going back");
                        return;
                    default:
                        System.out.println("Write correct number!");
                        scanner.nextLine();
                        choiceIsCorrect=false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                choiceIsCorrect=false;
                scanner.nextLine();
            }
        }while(!choiceIsCorrect);
    }
    public void editName(){
        String name = collectDataForItems.collectName();
        addToDatabase("name",name);
    }
    public void editPrice(){
        double price = collectDataForItems.collectPrice();
        addToDatabase("price",price);
    }
    public void editCategory(){
        String category = collectDataForItems.collectCategory();
        addToDatabase("category",category);
    }
    public void editQuantity(){
        int quantity = collectDataForItems.collectHowMuch();
        addToDatabase("quantity",quantity);
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
    public <T> void addToDatabase(String titleOfRow, T updatedData){
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "UPDATE itemsInShop SET " + titleOfRow + " = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,updatedData);
            statement.setString(2,editingData);
            statement.executeUpdate();
            System.out.println("Updated correctly");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
