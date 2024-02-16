package pl.onlineStore.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.users.Person;
public class LoginSqlConnection implements DataToConnectToSql {
    public boolean correctLoginAndPasswordCheck(String login, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT Password FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Person CollectAllDataFromDatabase(String login) throws SQLException {
        Person personToCollectData = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT Password, email, firstName, lastName, sex, dateOfBirth, phoneNumber, typeOfUser FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String passwordStored = resultSet.getString("password");
                String emailStored = resultSet.getString("email");
                String firstNameStored = resultSet.getString("firstName");
                String lastNameStored = resultSet.getString("lastName");
                String sexStored = resultSet.getString("sex");
                String dateOfBirthStored = resultSet.getString("dateOfBirth");
                String typeOfUserStored = resultSet.getString("typeOfUser");
                int phoneNumberStored = resultSet.getInt("phoneNumber");
                personToCollectData = new Person(login, passwordStored, emailStored, firstNameStored, lastNameStored, sexStored, dateOfBirthStored, phoneNumberStored, typeOfUserStored);
            } else {
                System.out.println("Collect person Data error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personToCollectData;
    }
}
