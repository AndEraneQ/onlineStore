package pl.onlineStore.menu;

import pl.onlineStore.ItemsInShop.ManageItems;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.users.User;


public class UserMenu {
    private Choice choice = new Choice();
    private User user = UserDataSingleton.getInstance().getUser();

    private void printStartMenu() {
        System.out.println("Choose an option:");
        System.out.println("1 - Go to the shop");
        System.out.println("2 - Manage your budget");
        System.out.println("3 - Show your purchase history");
        System.out.println("4 - Manage your data");
        System.out.println("5 - Logout");
        System.out.println("6 - Exit");
    }

    public void run() {
        System.out.println("Welcome in " + user.getLogin() + " account.");
        UserChoiceHandler userChoiceHandler = new UserChoiceHandler();
        ManageItems manageItems = new ManageItems();
        int userChoice;
        while (true) {
            printStartMenu();
            userChoice = choice.getIntChoice();
            switch (userChoice) {
                case 1:
                    userChoiceHandler.goToShopRun();
                    break;
                case 2:
                    userChoiceHandler.ManageBudgedRun();
                    break;
                case 3:
                    userChoiceHandler.showShoppingHistoryRun();
                    break;
                case 4:
                    userChoiceHandler.manageUserDataRun();
                    break;
                case 5:
                    manageItems.clearShoppingList();
                    StartingMenu startingMenu = new StartingMenu();
                    startingMenu.run();
                    return;
                case 6:
                    manageItems.clearShoppingList();
                    System.out.println("Thank you, see you soon!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }
}
