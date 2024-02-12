package org.onlineStorePackage.Actions;

import org.onlineStorePackage.SQL.SqlConnections;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserManageDataAction extends SqlConnections {
    private Scanner scanner = new Scanner(System.in);
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
        while(!moneyIsCorrect) {
            System.out.println("Type how much: ");
            money = scanner.nextLine();
            Double currentMoneyInAccount = checkMoneyBalance(login);
            try {
                if (money.startsWith("0") || !money.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                    System.out.println("Wrong type of number. Please type it correctly");
                }
                else if (operation == '-' && Double.parseDouble(money) > currentMoneyInAccount) {
                    System.out.println("Your balance is " + currentMoneyInAccount + ". Please type correct number.");
                }
                else {
                    moneyIsCorrect = true;
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
            Double moneyToDouble = Double.parseDouble(money);
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

