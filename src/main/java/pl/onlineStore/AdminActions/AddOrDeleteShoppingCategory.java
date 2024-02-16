package pl.onlineStore.AdminActions;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.*;
import java.util.Scanner;

public class AddOrDeleteShoppingCategory implements DataToConnectToSql {
    private Scanner scanner = new Scanner(System.in);
    private Choice choice = new Choice();
    public void addCategory(){
        System.out.println("Type category");
        String category = choice.getStringChoice();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String checkExistingCategorySQL = "SELECT category FROM shoppingCategory WHERE category = ?";
            PreparedStatement firstStatement = connection.prepareStatement(checkExistingCategorySQL);
            firstStatement.setString(1,category);
            ResultSet resultSet = firstStatement.executeQuery();
            if(!resultSet.next()){
                String addCategorySQL = "INSERT INTO shoppingCategory (category) VALUES (?)";
                PreparedStatement secondStatement = connection.prepareStatement(addCategorySQL);
                secondStatement.setString(1,category);
                secondStatement.executeUpdate();
                System.out.println("Added successfully");
            }
            else{
                System.out.println("This category was existing!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error during add category: ", e);
        }
    }
    public void removeCategory(){
        System.out.println("Type category to remove");
        String category = choice.getStringChoice();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "DELETE FROM shoppingCategory WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,category);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted==1){
                System.out.println("Deleted " + category + " category");
            }
            else{
                System.out.println("This category didn't exist.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error during delete category: ", ex);
        }
    }
}
