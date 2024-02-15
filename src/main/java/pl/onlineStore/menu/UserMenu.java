package pl.onlineStore.menu;
import pl.onlineStore.ItemsInShop.ManageItems;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {
    private Scanner scanner = new Scanner(System.in);
    private User user = UserDataSingleton.getInstance().getUser();

    private void printStartMenu(){
        System.out.println("Choose an option:");
        System.out.println("1 - Go to the shop");
        System.out.println("2 - Manage your budget");
        System.out.println("3 - Show your purchase history");
        System.out.println("4 - Manage your data");
        System.out.println("5 - Logout");
        System.out.println("6 - Exit");
    }
    public void run(){
        System.out.println("Welcome in " + user.getLogin() + " account.");
        int userChoice = 0;
        boolean userCorrectChoice;
        do{
            printStartMenu();
            try {
                int taskChoosenByUser = scanner.nextInt();
                userCorrectChoice=true;
                UserChoiceHandler userChoiceHandler = new UserChoiceHandler();
                ManageItems manageItems = new ManageItems();
                switch (taskChoosenByUser) {
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
                        userCorrectChoice=false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                userCorrectChoice=false;
                scanner.nextLine();
            }
        } while(!userCorrectChoice || userChoice!=5 || userChoice!=6);
    }
}
