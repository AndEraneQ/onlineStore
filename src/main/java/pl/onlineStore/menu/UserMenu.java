package pl.onlineStore.menu;
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
        System.out.println("4 - Edit your data");
        System.out.println("5 - Logout");
        System.out.println("6 - Exit");
    }
    private void printManageBudgetMenu(){
        System.out.println("Manage you budget!!!");
        System.out.println("1 - Deposit money");
        System.out.println("2 - Withdraw money");
        System.out.println("3 - Check your balance");
        System.out.println("4 - Back");
    }
    public void run(){
        System.out.println("Welcome in " + user.getLogin() + " account.");
        System.out.println(user.getLogin());
        int userChoice;
        boolean userCorrectChoice = false;
        while(!userCorrectChoice) {
            printStartMenu();
            try {
                int taskChoosenByUser = scanner.nextInt();
                switch (taskChoosenByUser) {
                    case 1:
                        break;
                    case 2:
                        int choice;
                        boolean userWantBack = false;
                        UserChoicesHandler userChoicesMenu = new UserChoicesHandler();
                        while(!userWantBack){
                            printManageBudgetMenu();
                            try {
                                choice = scanner.nextInt();
                                userWantBack = userChoicesMenu.ifManageBudgedRun(choice);
                            } catch (InputMismatchException e) {
                                System.out.println("You need to write a number! Please try again");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        userCorrectChoice = true;
                        StartingMenu startingMenu = new StartingMenu();
                        startingMenu.run();
                        break;
                    case 6:
                        userCorrectChoice = true;
                        System.out.println("Thank you, see you soon!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
            }
        }
    }
}
