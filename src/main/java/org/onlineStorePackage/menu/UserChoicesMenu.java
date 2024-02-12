package org.onlineStorePackage.menu;

import org.onlineStorePackage.Actions.UserManageDataAction;

import java.util.Scanner;

public class UserChoicesMenu {
    private Scanner scanner = new Scanner(System.in);

    public void changeDataRun() {

    }

    public void showHistoryRun() {

    }

    public void goToShopRun() {

    }

    public boolean ifManageBudgedRun(int choice, String login) {
        UserManageDataAction userManageDataAction = new UserManageDataAction();
        switch (choice) {
            case 1:
                userManageDataAction.depositOrWithdrawMoney(login, '+');
                break;
            case 2:
                userManageDataAction.depositOrWithdrawMoney(login, '-');
                break;
            case 3:
                System.out.println("Your balance is " + userManageDataAction.checkMoneyBalance(login));
                break;
            case 4:
                System.out.println("Backing to menu.");
                return true;
            default:
                System.out.println("Invalid choice. Try again");
                break;
        }
        return false;
    }
}
