package org.onlineStorePackage.menu;
import org.onlineStorePackage.authentication.Login;
import org.onlineStorePackage.authentication.Register;
import java.util.InputMismatchException;
import java.util.Scanner;
public class StartingMenu {
    private Scanner scanner = new Scanner(System.in);
    private void printOption(){
        System.out.println("Welcome in online store.");
        System.out.println("Select an option: ");
        System.out.println("1 - login.");
        System.out.println("2 - register");
        System.out.println("3 - exit");
        System.out.println("Enter your choose: ");
    }
    public void run(){
        int userChoice;
        boolean correctChoice = false;
        while(!correctChoice) {
            printOption();
            try {
                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        Login login = new Login();
                        correctChoice = true;
                        login.run();
                        break;
                    case 2:
                        Register register = new Register();
                        correctChoice = true;
                        register.run();
                        break;
                    case 3:
                        System.out.println("Thank you, see you soon!");
                        correctChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choose. Please try again");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}