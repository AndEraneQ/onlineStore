package pl.onlineStore.AdminActions;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.ItemsInShop.Item;
import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddItemsToShop implements DataToConnectToSql {
    public void addItem() {
        System.out.println("Add item");
        CollectDataForItems collectDataForItems = new CollectDataForItems();
        Item item = new Item(collectDataForItems.collectAllDataForItem());
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
}
