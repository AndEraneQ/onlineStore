package pl.onlineStore.menu;

import pl.onlineStore.UserActions.GoToTheShopAction;
import pl.onlineStore.UserActions.ShowPurchaseHistoryAction;
import pl.onlineStore.UserActions.UserManageBudgetAction;
import pl.onlineStore.UserActions.UserManageUserDataAction;
import pl.onlineStore.choices.Choice;


public class UserChoiceHandler {
    private Choice choice = new Choice();

    private void printManageBudgetMenu() {
        System.out.println("Manage you budget!!!");
        System.out.println("1 - Deposit money");
        System.out.println("2 - Withdraw money");
        System.out.println("3 - Check your balance");
        System.out.println("4 - Back");
    }

    private void printManageUserDataMenu() {
        System.out.println("Manage your data!!!");
        System.out.println("1 - show your data");
        System.out.println("2 - edit your data");
        System.out.println("3 - Back");
    }

    private void printGoToTheShopMenu() {
        System.out.println("1 - Add new product to your cart");
        System.out.println("2 - Delete product from your cart");
        System.out.println("3 - Check your cart");
        System.out.println("4 - Buy products from your cart");
        System.out.println("5 - Back");
    }

    public void manageUserDataRun() {
        int userChoice;
        UserManageUserDataAction userManageUserDataAction = new UserManageUserDataAction();
        while (true) {
            printManageUserDataMenu();
            userChoice = choice.getIntChoice();
            switch (userChoice) {
                case 1:
                    userManageUserDataAction.showUserData();
                    break;
                case 2:
                    userManageUserDataAction.editUserData();
                    break;
                case 3:
                    System.out.println("Backing to menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again");
                    break;
            }
        }
    }

    public void showShoppingHistoryRun() {
        ShowPurchaseHistoryAction showPurchaseHistoryAction = new ShowPurchaseHistoryAction();
        showPurchaseHistoryAction.run();
    }

    public void goToShopRun() {
        int userChoice;
        GoToTheShopAction goToTheShopAction = new GoToTheShopAction();
        while (true) {
            printGoToTheShopMenu();
            userChoice = choice.getIntChoice();
            switch (userChoice) {
                case 1:
                    goToTheShopAction.addNewProductToYourCart();
                    break;
                case 2:
                    goToTheShopAction.deleteItemFromYourCart();
                    break;
                case 3:
                    goToTheShopAction.checkYourCart();
                    break;
                case 4:
                    goToTheShopAction.buyProductsFromCart();
                    break;
                case 5:
                    System.out.println("Backing to menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again");
                    break;
            }
        }
    }

    public void ManageBudgedRun() {
        int userChoice;
        UserManageBudgetAction userManageBudgetAction = new UserManageBudgetAction();
        while (true) {
            printManageBudgetMenu();
            userChoice = choice.getIntChoice();
            switch (userChoice) {
                case 1:
                    userManageBudgetAction.depositOrWithdrawMoney('+');
                    break;
                case 2:
                    userManageBudgetAction.depositOrWithdrawMoney('-');
                    break;
                case 3:
                    System.out.println("Your balance is " + userManageBudgetAction.checkMoneyBalance());
                    break;
                case 4:
                    System.out.println("Backing to menu.");
                    return;
                default:
                    System.out.println("Invalid userChoice. Try again");
                    break;
            }
        }
    }

}
