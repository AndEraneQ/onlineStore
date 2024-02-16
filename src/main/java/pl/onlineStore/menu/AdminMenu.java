package pl.onlineStore.menu;

import pl.onlineStore.choices.Choice;
import pl.onlineStore.users.Admin;
import pl.onlineStore.Singletons.AdminDataSingleton;

public class AdminMenu {
    private Choice choice = new Choice();

    private void printStartMenu() {
        System.out.println("Choose an option:");
        System.out.println("1 - Add or delete someone admin rights.");
        System.out.println("2 - Add or delete item to shop");
        System.out.println("3 - Edit items data in shop");
        System.out.println("4 - Add or delete new category in shop");
        System.out.println("5 - Logout");
        System.out.println("6 - Exit");
    }

    public void adminMenuOptionRun() {
        Admin admin = AdminDataSingleton.getInstance().getAdmin();
        System.out.println("Welcome in " + admin.getLogin() + " account.");
        AdminChoicesHandler adminChoicesHandler = new AdminChoicesHandler();
        int userChoice;
        while (true) {
            printStartMenu();
            userChoice = choice.getIntChoice();
            switch (userChoice) {
                case 1:
                    adminChoicesHandler.addOrDeleteAdminRights();
                    break;
                case 2:
                    adminChoicesHandler.addOrDeleteItemsInShop();
                    break;
                case 3:
                    adminChoicesHandler.editItemsDataInShop();
                    break;
                case 4:
                    adminChoicesHandler.AddOrDeleteNewCategoryInShop();
                    break;
                case 5:
                    StartingMenu startingMenu = new StartingMenu();
                    startingMenu.run();
                    return;
                case 6:
                    System.out.println("Thank you, see you soon!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }

    public void run() {
        String userChoice;
        do {
            System.out.println("Please choose type of login (admin or user)");
            userChoice = choice.getStringChoice();
        } while (!userChoice.equals("admin") && !userChoice.equals("user"));
        if (userChoice.equals("user")) {
            UserMenu userMenu = new UserMenu();
            userMenu.run();
        } else {
            adminMenuOptionRun();
        }
    }
}