package org.onlineStorePackage.users;
import org.onlineStorePackage.SQL.SqlConnections;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UsersActions extends SqlConnections {
    private Scanner scanner = new Scanner(System.in);
    public void changeDataRun() {

    }

    public void showHistoryRun() {

    }

    public boolean ifManageBudgedRun(int choice, String login) {
        switch (choice) {
            case 1:
                depositOrWithdrawMoney(login,'+');
                return true;
            case 2:
                depositOrWithdrawMoney(login,'-');
                return true;
            case 3:
                System.out.println("Your balance is " + checkMoneyBalance(login));
                return true;
            case 4:
                System.out.println("Backing to menu.");
                return true;
            default:
                System.out.println("Invalid choice. Try again");
                return false;
        }
    }

    public void goToShopRun() {

    }

    public double checkMoneyBalance(String login) {
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT balance FROM accountBalance WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void depositOrWithdrawMoney(String login,Character operation){
        boolean moneyIsCorrect = false;
        String money = null;
        Double moneyToDouble = null;
        while(!moneyIsCorrect) {
            System.out.println("Type how much: ");
            money = scanner.nextLine();
            moneyToDouble = Double.parseDouble(money);
            Double currentMoneyInAccount = checkMoneyBalance(login);
            try {
                if(operation.equals('-') && moneyToDouble>currentMoneyInAccount) {
                    System.out.println("Your balance is " + currentMoneyInAccount + ". Please type correct number.");
                }
                else if(!money.startsWith("0") && money.matches("\\d+(\\.\\d{1,2})?")){
                    moneyIsCorrect=true;
                }
                else{
                    System.out.println("Wrong type of number. Please type it correctly");
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
            }
        }
        try {
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "UPDATE accountBalance SET balance = balance " + operation + " ? WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1,moneyToDouble);
            statement.setString(2,login);
            if(statement.executeUpdate()>0){
                System.out.println(operation + " " + money + " To your account");
            }
            else{
                System.out.println("Error with deposit money.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
