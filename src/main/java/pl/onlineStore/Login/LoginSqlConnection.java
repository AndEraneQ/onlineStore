package pl.onlineStore.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.users.Person;
public class LoginSqlConnection implements DataToConnectToSql {
    public boolean correctLoginAndPasswordCheck(String login,String password){
        try{
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT Password FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (storedPassword.equals(password)) {
                    return true;
                }
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public Person CollectAllDataFromDatabase(String login) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "SELECT Password, email, firstName, lastName, sex, dateOfBirth, phoneNumber, typeOfUser FROM users WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        String passwordStored = null, emailStored=null, firstNameStored=null, lastNameStored=null, sexStored=null, dateOfBirthStored=null, typeOfUserStored=null;
        int phoneNumberStored = 0;
        if (resultSet.next()) {
            passwordStored = resultSet.getString("password");
            emailStored = resultSet.getString("email");
            firstNameStored = resultSet.getString("firstName");
            lastNameStored = resultSet.getString("lastName");
            sexStored = resultSet.getString("sex");
            dateOfBirthStored = resultSet.getString("dateOfBirth");
            typeOfUserStored = resultSet.getString("typeOfUser");
            phoneNumberStored = resultSet.getInt("phoneNumber");
        } else{
            System.out.println("Collect person Data error");
        }
        Person personToCollectData = new Person(login,passwordStored,emailStored,firstNameStored,lastNameStored,sexStored,dateOfBirthStored,phoneNumberStored,typeOfUserStored);
        return personToCollectData;
    }
}
