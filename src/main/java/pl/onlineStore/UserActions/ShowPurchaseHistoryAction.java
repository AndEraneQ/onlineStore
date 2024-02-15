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
            String nameOfItem;
            int howMuchWasBought;
            double howMuchWasPaid;
            System.out.println("Your shopping history:");
            while(resultSet.next()){
                nameOfItem = resultSet.getString("nameOfItem");
                howMuchWasBought = resultSet.getInt("howMuchWasBought");
                howMuchWasPaid = resultSet.getDouble("howMuchWasPaid");
                System.out.println("Name of product: " + nameOfItem + " quantity: " + howMuchWasBought + " paid: " + howMuchWasPaid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
