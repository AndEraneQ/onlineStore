package pl.onlineStore.AdminActions;
import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.ItemsInShop.ManageItems;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.choices.Choice;

import java.sql.*;
import java.util.ArrayList;

public class EditItemsDataAction implements DataToConnectToSql {
    private CollectDataForItems collectDataForItems = new CollectDataForItems();
    private Choice choice = new Choice();
    private String editingCategory;
    private String editingDataName;
    private void displayEditMenu(){
        System.out.println("Choose what you want to change: ");
        System.out.println("1 - edit name");
        System.out.println("2 - edit category");
        System.out.println("3 - edit price");
        System.out.println("4 - edit quantity");
        System.out.println("5 - back");
    }
    public void editItemsRun(){
            System.out.println("Select category: ");
            editingCategory = collectDataForItems.collectCategory();
            ManageItems manageItems = new ManageItems();
            ArrayList<String> listOfItemsInCategory = manageItems.collectListOfItemsNamesFromCategory(editingCategory);
            if(listOfItemsInCategory.size()==0){
                System.out.println("There is 0 items in this category");
                return;
            }
            boolean editingDataIsCorrect = false;
            do {
                System.out.println("Select item from list: ");
                for (String nameOfItem : listOfItemsInCategory) {
                    System.out.print(nameOfItem + " ");
                }
                System.out.println();
                editingDataName = choice.getStringChoice();
                for(String nameOfItem : listOfItemsInCategory){
                    if(nameOfItem.equals(editingDataName)){
                        editingDataIsCorrect=true;
                    }
                }
            } while(!editingDataIsCorrect);
        boolean validChoice;
        do {
            validChoice = true;
            displayEditMenu();
            int userChoice = choice.getIntChoice();
            switch (userChoice) {
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
                    System.out.println("Please enter a correct number!!");
                    validChoice = false;
                    break;
            }
        }while(!validChoice);
    }
    public void editName(){
        String name = collectDataForItems.collectName();
        addToDatabaseEditedData("name",name);
    }
    public void editPrice(){
        double price = collectDataForItems.collectPrice();
        addToDatabaseEditedData("price",price);
    }
    public void editCategory(){
        String category = collectDataForItems.collectCategory();
        addToDatabaseEditedData("category",category);
    }
    public void editQuantity(){
        int quantity = collectDataForItems.collectHowMuch();
        addToDatabaseEditedData("quantity",quantity);
    }
    public <T> void addToDatabaseEditedData(String titleOfRow, T updatedData){
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "UPDATE itemsInShop SET " + titleOfRow + " = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,updatedData);
            statement.setString(2, editingDataName);
            statement.executeUpdate();
            System.out.println("Updated successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
