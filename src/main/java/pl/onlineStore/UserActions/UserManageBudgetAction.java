package pl.onlineStore.UserActions;
import pl.onlineStore.SQL.DataToConnectToSql;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.User;

public class UserManageBudgetAction implements DataToConnectToSql {
    private Scanner scanner = new Scanner(System.in);
    private User user = UserDataSingleton.getInstance().getUser();
    private boolean ifUserWantGoBack(){
        boolean numberIsCorrect = false;
        while(!numberIsCorrect) {
            System.out.println("1 - Go back");
            System.out.println("2 - Try again");
            try {
                int choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        return true;
                    case 2:
                        scanner.nextLine();
                        return false;
                    default:
                        System.out.println("Write Correct number!");
                        scanner.nextLine();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
            }
        }
        return false;
    }
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
            scanner.nextLine();
        }
        return -1;
    }
    public void depositOrWithdrawMoney(Character operation) {
        boolean moneyIsCorrect = false, userWantGoBack = false;
        String moneyWrittenByUser = null;
        while (!moneyIsCorrect) {
            System.out.println("Type how much: ");
            moneyWrittenByUser = scanner.nextLine();
            Double currentMoneyInAccount = checkMoneyBalance();
            try {
                if (moneyWrittenByUser.startsWith("0") || !moneyWrittenByUser.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                    System.out.println("Wrong type of number. Please type it correctly");
                    userWantGoBack = ifUserWantGoBack();
                } else if (operation == '-' && Double.parseDouble(moneyWrittenByUser) > currentMoneyInAccount) {
                    System.out.println("Your balance is " + currentMoneyInAccount + ". Please type correct number.");
                    userWantGoBack = ifUserWantGoBack();
                } else {
                    moneyIsCorrect = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
                userWantGoBack = ifUserWantGoBack();
            }
        }
        if (userWantGoBack) {
            return;
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

