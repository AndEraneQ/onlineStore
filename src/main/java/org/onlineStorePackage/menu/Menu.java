package org.onlineStorePackage.menu;
import org.onlineStorePackage.authentication.Login;
import org.onlineStorePackage.authentication.Register;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    private void printOption(){
        System.out.println("Welcome in online store.");
        System.out.println("Select an option: ");
        System.out.println("1 - login.");
        System.out.println("2 - register");
        System.out.println("3 - exit");
        System.out.println("Enter your choose: ");
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        int userChoise;
        printOption();
        try {
            userChoise = scanner.nextInt();
            switch (userChoise) {
                case 1:
                    Login login = new Login();
                    login.run();
                    break;
                case 2:
                    Register register = new Register();
                    register.run();
                    break;
                case 3:
                    System.out.println("Thank you, see you soon!");
                    break;
                default:
                    System.out.println("Invalid choose. Please try again");
                    run();
                    break;
            }
        }
            catch(InputMismatchException e){
                System.out.println("You need to write a number! Please try again");
                run();
            }
        scanner.close();
    }
}
