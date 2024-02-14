package pl.onlineStore.UserActions;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.User;
import pl.onlineStore.Register.CollectDataFromUser;
import java.sql.*;
import java.util.*;

public class UserManageUserDataAction implements DataToConnectToSql {
    private User user = UserDataSingleton.getInstance().getUser();
    public void editUserData(){
        Scanner scanner = new Scanner(System.in);
        CollectDataFromUser collectDataFromUser = new CollectDataFromUser();
        Map<String, Runnable> functionMapCollectDataFromUser = new HashMap<>();
        functionMapCollectDataFromUser.put("password",collectDataFromUser::collectPassword);
        functionMapCollectDataFromUser.put("email",collectDataFromUser::collectEmail);
        functionMapCollectDataFromUser.put("first name",collectDataFromUser::collectFirstName);
        functionMapCollectDataFromUser.put("last name",collectDataFromUser::collectLastName);
        functionMapCollectDataFromUser.put("sex",collectDataFromUser::collectSex);
        functionMapCollectDataFromUser.put("phone number",collectDataFromUser::collectPhoneNumber);
        boolean dataIsCorrect = false;
        do {
            System.out.println("Write what you want to change: ");
            String userProvidedData = scanner.nextLine();
            for (String data : functionMapCollectDataFromUser.keySet()) {
                if (userProvidedData.equals(data)) {
                    dataIsCorrect = true;
                }
            }
            if (dataIsCorrect) {
                Runnable functionCollectDataFromUser = functionMapCollectDataFromUser.get(userProvidedData);
                functionCollectDataFromUser.run();
                try {
                    Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
                    String sql = "UPDATE users SET password = ?, email = ?, firstName = ?, lastName = ?, sex = ?, phoneNumber = ? WHERE login = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, user.getPassword());
                    statement.setString(2, user.getEmail());
                    statement.setString(3, user.getFirstName());
                    statement.setString(4, user.getLastName());
                    statement.setString(5, user.getSex());
                    statement.setInt(6, user.getPhoneNumber());
                    statement.setString(7, user.getLogin());
                    statement.executeUpdate();
                    System.out.println("Updated data");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Choose correct option from: ");
                for (String data : functionMapCollectDataFromUser.keySet()) {
                    System.out.print(data + ", ");
                }
                System.out.println();
            }
        }while(!dataIsCorrect);
    }
    public void showUserData(){
        user.printYourData();
    }
}
