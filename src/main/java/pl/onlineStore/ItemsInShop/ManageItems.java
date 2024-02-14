package pl.onlineStore.ItemsInShop;

import pl.onlineStore.SQL.DataToConnectToSql;
import java.sql.*;
import java.util.ArrayList;

public class ManageItems implements DataToConnectToSql {
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
}
