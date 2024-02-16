package pl.onlineStore.AdminActions;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.ItemsInShop.Item;
import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddOrDeleteItems implements DataToConnectToSql {
    public void addItem() {
        System.out.println("Add item");
        Item item = new Item();
        CollectDataForItems collectDataForItems = new CollectDataForItems();
        item.setName(collectDataForItems.collectName());
        item.setCategory(collectDataForItems.collectCategory());
        item.setPrice(collectDataForItems.collectPrice());
        item.setQuantity(collectDataForItems.collectHowMuch());
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "INSERT INTO itemsInShop VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, item.getName());
            statement.setInt(2, item.getQuantity());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, item.getCategory());
            if (statement.executeUpdate() > 0) {
                System.out.println("Item added successfully.");
            } else {
                System.out.println("Failed to add item.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while adding item: " + e.getMessage());
        }
    }
    public void removeItem(){
        System.out.println("Deleting item.");
        System.out.println("Type item to delete: ");
        Choice choice = new Choice();
        String itemToDelete = choice.getStringChoice();
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "DELETE FROM itemsInShop WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,itemToDelete);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 1) {
                System.out.println("Item '" + itemToDelete + "' deleted successfully.");
            } else {
                System.out.println("Item '" + itemToDelete + "' does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while deleting item: " + e.getMessage());
        }
    }
}
