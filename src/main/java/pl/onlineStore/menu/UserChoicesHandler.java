package pl.onlineStore.menu;

import pl.onlineStore.Actions.UserManageBudgetAction;

import java.util.Scanner;

public class UserChoicesHandler {
    private Scanner scanner = new Scanner(System.in);

    public void changeDataRun() {

    }

    public void showHistoryRun() {

    }

    public void goToShopRun() {

    }

    public boolean ifManageBudgedRun(int choice) {
        UserManageBudgetAction userManageDataAction = new UserManageBudgetAction();
        switch (choice) {
            case 1:
                userManageDataAction.depositOrWithdrawMoney('+');
                break;
            case 2:
                userManageDataAction.depositOrWithdrawMoney('-');
                break;
            case 3:
                System.out.println("Your balance is " + userManageDataAction.checkMoneyBalance());
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
