package org.onlineStorePackage.SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class SqlLogin extends SqlConnections {
    private String typeOfUser;
    public void setTypeOfUser(String typeOfUser){
        this.typeOfUser=typeOfUser;
    }
    public String getTypeOfUser(){
        return typeOfUser;
    }
    public boolean correctLoginAndPasswordCheck(String Login,String Password){
        try{
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT Password, typeOfUser FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                setTypeOfUser(resultSet.getString("typeOfUser"));
                if (storedPassword.equals(Password)) {
                    return true;
                }
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
