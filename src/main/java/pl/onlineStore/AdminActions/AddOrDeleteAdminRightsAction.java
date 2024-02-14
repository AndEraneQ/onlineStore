package pl.onlineStore.AdminActions;

import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.AdminDataSingleton;
import pl.onlineStore.users.Admin;
import java.sql.*;
import java.util.Scanner;

public class AddOrDeleteAdminRightsAction implements DataToConnectToSql {
    private Admin admin = AdminDataSingleton.getInstance().getAdmin();
    private void setUserOrAdminLaws(String lawsToSet){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type login to changes laws: ");
        String loginToChangeLaws = scanner.nextLine();
        if(loginToChangeLaws.equals(admin.getLogin())) {
            System.out.println("You cant modify your own laws!");
            return;
        }
        String typeOfUser="";
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT typeOfUser FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,loginToChangeLaws);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                typeOfUser = resultSet.getString("typeOfUser");
            }
            else{
                System.out.println("User not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!typeOfUser.equals(lawsToSet)){
            try {
                Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
                String sql = "UPDATE users SET typeOfUser = ? WHERE login = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, lawsToSet);
                statement.setString(2, loginToChangeLaws);
                statement.executeUpdate();
                System.out.println("Changed laws correctly");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("User is a " + lawsToSet + "!");
        }
    }
    public void addLaws(){
        setUserOrAdminLaws("admin");
    }
    public void deleteLaws(){
        setUserOrAdminLaws("user");
    }
}
