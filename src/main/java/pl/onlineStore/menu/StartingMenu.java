package pl.onlineStore.menu;

import pl.onlineStore.Login.Login;
import pl.onlineStore.Register.Register;
import pl.onlineStore.choices.Choice;

public class StartingMenu {
    private void printOption() {
        System.out.println("Welcome in online store.");
        System.out.println("Select an option: ");
        System.out.println("1 - login.");
        System.out.println("2 - register");
        System.out.println("3 - exit");
        System.out.println("Enter your choose: ");
    }

    public void run() {
        int userChoice;
        Choice choice = new Choice();
        while (true) {
            printOption();
            userChoice = choice.getIntChoice();
            switch (userChoice) {
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
                    return;
                default:
                    System.out.println("Invalid choose. Please try again");
                    break;
            }
        }
    }
}
