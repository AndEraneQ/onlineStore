package pl.onlineStore.AdminActions;

import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.AdminDataSingleton;
import pl.onlineStore.users.Admin;
import java.sql.*;
import java.util.Scanner;

public class AddOrDeleteAdminLaws implements DataToConnectToSql {
    private Admin admin = AdminDataSingleton.getInstance().getAdmin();

    public void addAdminLaws(){
        String login = collectLogin("admin");
        setUserOrAdminLaws(login, "admin");
    }
    public void deleteAdminLaws(){
        String login = collectLogin("user");
        setUserOrAdminLaws(login, "user");
    }
    private String collectLogin(String typeOfUserToSetLater) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type login to changes laws: ");
        String loginToChangeLaws = scanner.nextLine();
        if (loginToChangeLaws.equals(admin.getLogin())) {
            System.out.println("You cant modify your own laws!");
            return "admin";
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT typeOfUser FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginToChangeLaws);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String currentUserLaws = resultSet.getString("typeOfUser");
                if(currentUserLaws.equals(typeOfUserToSetLater) && !loginToChangeLaws.equals(admin.getLogin())){
                    System.out.println("User is a " + currentUserLaws + "!");
                } else {
                    return loginToChangeLaws;
                }
            } else {
                System.out.println("User not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private void setUserOrAdminLaws(String login, String lawsToSet){
        if(login!=null){
            if(login.equals("admin")){
                return;
            }
            Connection connection=null;
            try {
                connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
                String sql = "UPDATE users SET typeOfUser = ? WHERE login = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, lawsToSet);
                statement.setString(2, login);
                statement.executeUpdate();
                System.out.println("Changed laws correctly");
            } catch (SQLException e) {
                System.out.println("Error occurred while setting user laws: " + e.getMessage());
            }
        }
    }
}
