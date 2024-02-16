package pl.onlineStore.Register;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.users.User;

import java.sql.*;

public class RegisterSqlConnection implements DataToConnectToSql {
    public boolean ifUserExist(String loginToCheck){
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT login, typeOfUser FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,loginToCheck);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String dataBaseLogin = resultSet.getString("login");
                if(dataBaseLogin.equals(loginToCheck)){
                    System.out.println("User with login '" + loginToCheck + "' already exist. Try again.");
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean addUserToUserDataBase(User user)  {
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getSex());
            statement.setString(7, user.getDateOfBirth());
            statement.setString(8, String.valueOf(user.getPhoneNumber()));
            statement.setString(9, "user");
            return statement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public boolean addUserToAccountBalanceDataBase(User user){
        try{
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "INSERT INTO accountBalance VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, String.valueOf(0.00));
            return statement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
