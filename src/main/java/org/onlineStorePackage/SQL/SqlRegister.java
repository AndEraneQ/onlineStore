package org.onlineStorePackage.SQL;

import java.sql.*;

public class SqlRegister extends SqlConnections{
    public boolean userExistError(String login){
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT login, typeOfUser FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String dataBaseLogin = resultSet.getString("login");
                if(dataBaseLogin.equals(login)){
                    System.out.println("User with login '" + login + "' exist. Try again.");
                    return true;
                }
                    return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
