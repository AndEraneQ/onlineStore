package pl.onlineStore.UserActions;

import pl.onlineStore.SQL.DataToConnectToSql;

import java.sql.*;

import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.User;
import pl.onlineStore.choices.Choice;

public class UserManageBudgetAction implements DataToConnectToSql {
    private User user = UserDataSingleton.getInstance().getUser();
    private Choice choice = new Choice();

    public double checkMoneyBalance() {
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT balance FROM accountBalance WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void depositOrWithdrawMoney(Character operation) {
        String moneyWrittenByUser;
        Double currentMoneyInAccount;
        while (true) {
            System.out.println("Type how much: ");
            moneyWrittenByUser = choice.getStringChoice();
            currentMoneyInAccount = checkMoneyBalance();
            if (moneyWrittenByUser.startsWith("0") || !moneyWrittenByUser.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                System.out.println("Wrong type of number. Please type it correctly");
            } else if (operation == '-' && Double.parseDouble(moneyWrittenByUser) > currentMoneyInAccount) {
                System.out.println("Your balance is " + currentMoneyInAccount + ". Please type correct number.");
            } else {
                break;
            }
            try {
                Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
                String sql = "UPDATE accountBalance SET balance = balance " + operation + " ? WHERE login = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                Double moneyToDouble = Double.parseDouble(moneyWrittenByUser);
                statement.setDouble(1, moneyToDouble);
                statement.setString(2, user.getLogin());
                if (statement.executeUpdate() > 0) {
                    System.out.println(operation + " " + moneyWrittenByUser + " To your account");
                } else {
                    System.out.println("Error with deposit moneyWrittenByUser.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void depositOrWithdrawMoney(Character operation, double howMuch) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "UPDATE accountBalance SET balance = balance " + operation + " ? WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, howMuch);
            statement.setString(2, user.getLogin());
            if (statement.executeUpdate() > 0) {
                return;
            } else {
                System.out.println("Error with deposit moneyWrittenByUser.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

