package pl.onlineStore.UserActions;

import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.UserDataSingleton;

import java.sql.*;

import pl.onlineStore.users.User;

public class ShowPurchaseHistoryAction implements DataToConnectToSql {
    private User user = UserDataSingleton.getInstance().getUser();
    public void run(){
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT * FROM purchaseHistory WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Your shopping history:");
            while(resultSet.next()){
                String nameOfItem = resultSet.getString("nameOfItem");
                int howMuchWasBought = resultSet.getInt("howMuchWasBought");
                double howMuchWasPaid = resultSet.getDouble("howMuchWasPaid");
                System.out.println("Name of product: " + nameOfItem + " quantity: " + howMuchWasBought + " paid: " + howMuchWasPaid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
