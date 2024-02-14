package pl.onlineStore.AdminActions;

import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.ItemsInShop.Item;
import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddOrDeleteItemsAction implements DataToConnectToSql {
    private Scanner scanner = new Scanner(System.in);
    private CollectDataForItems collectDataForItems = new CollectDataForItems();
    public void addItem() {
        System.out.println("Add item");
        Item item = new Item();
        item.setName(collectDataForItems.collectName());
        item.setCategory(collectDataForItems.collectCategory());
        item.setPrice(collectDataForItems.collectPrice());
        item.setHowMuch(collectDataForItems.collectHowMuch());
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "INSERT INTO itemsInShop VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, item.getName());
            statement.setInt(2, item.getHowMuch());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, item.getCategory());
            if (statement.executeUpdate() > 0) {
                System.out.println("Added correctly");
            } else{
                System.out.println("Add item error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeItem(){
        System.out.println("Deleting item.");
        System.out.println("Type item to delete: ");
        String itemToDelete = scanner.nextLine();
        try{
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "DELETE FROM itemsInShop WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,itemToDelete);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted==1){
                System.out.println("Deleted " + itemToDelete);
            } else{
                System.out.println("This item did not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
