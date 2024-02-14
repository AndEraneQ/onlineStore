package pl.onlineStore.menu;
import pl.onlineStore.users.Admin;
import pl.onlineStore.Singletons.AdminDataSingleton;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    private Scanner scanner = new Scanner(System.in);
    private void printStartMenu(){
        System.out.println("Choose an option:");
        System.out.println("1 - Add or delete someone admin rights.");
        System.out.println("2 - Add or delete item to shop");
        System.out.println("3 - Edit items data in shop");
        System.out.println("4 - Add or delete new category in shop");
        System.out.println("5 - Logout");
        System.out.println("6 - Exit");
    }
    public void adminMenuOptions(){
        Admin admin = AdminDataSingleton.getInstance().getAdmin();
        System.out.println("Welcome in " + admin.getLogin() + " account.");
        int userChoice = 0;
        boolean userCorrectChoice;
        do{
            AdminChoicesHandler adminChoicesHandler = new AdminChoicesHandler();
            printStartMenu();
            try {
                int taskChoosenByUser = scanner.nextInt();
                userCorrectChoice=true;
                switch (taskChoosenByUser) {
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
                        userCorrectChoice=false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                userCorrectChoice=false;
                scanner.nextLine();
            }
        } while(!userCorrectChoice || userChoice!=6 || userChoice!=7);
    }
    public void run(){
        String userChoice;
        do {
            System.out.println("Choose whether you want to log in as an admin or a user.");
            userChoice = scanner.nextLine();
        } while(!userChoice.equals("admin") && !userChoice.equals("user"));
        if(userChoice.equals("user")){
            UserMenu userMenu = new UserMenu();
            userMenu.run();
        }
        else{
            adminMenuOptions();
        }
    }
}