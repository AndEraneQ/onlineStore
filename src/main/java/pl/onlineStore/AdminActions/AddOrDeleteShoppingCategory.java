package pl.onlineStore.AdminActions;

import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.*;
import java.util.Scanner;

public class AddOrDeleteShoppingCategory implements DataToConnectToSql {
    private Scanner scanner = new Scanner(System.in);
    public void addCategory(){
        System.out.println("Type category to add");
        String category = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String checkExistingCategorySQL = "SELECT category FROM shoppingCategory WHERE category = ?";
            PreparedStatement firstStatement = connection.prepareStatement(checkExistingCategorySQL);
            firstStatement.setString(1,category);
            ResultSet resultSet = firstStatement.executeQuery();
            if(!resultSet.next()){
                String addCategorySQL = "INSERT INTO shoppingCategory (category) VALUES (?)";
                PreparedStatement secondStatement = connection.prepareStatement(addCategorySQL);
                secondStatement.setString(1,category);
                secondStatement.executeUpdate();
                System.out.println("Added succesfully");
            }
            else{
                System.out.println("This category existing!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeCategory(){
        System.out.println("Type category to remove");
        String category = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "DELETE FROM shoppingCategory WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,category);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted==1){
                System.out.println("Deleted " + category + " category");
            }
            else{
                System.out.println("This category did not exist.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
