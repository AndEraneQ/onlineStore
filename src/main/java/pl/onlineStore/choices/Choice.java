package pl.onlineStore.choices;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Choice {
    private Scanner scanner = new Scanner(System.in);
    public int getIntChoice() {
        int choice;
        while(true){
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                scanner.nextLine();
                continue;
            }
            break;
        }
        return choice;
    }
    public String getStringChoice() {
        String choice;
        while(true){
            try {
                choice = scanner.nextLine();
                if (choice.isEmpty()) {
                    throw new InputMismatchException("Empty input. Please enter a valid string.");
                }
            } catch (InputMismatchException e) {
                continue;
            }
            break;
        }
        return choice;
    }
}

