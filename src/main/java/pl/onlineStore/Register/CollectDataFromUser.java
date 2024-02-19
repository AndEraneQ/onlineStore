package pl.onlineStore.Register;

import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.users.User;

import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class CollectDataFromUser {
    private CheckDataToRegister checkDataToRegister = new CheckDataToRegister();
    private Choice choice = new Choice();
    public User user = UserDataSingleton.getInstance().getUser();

    public String collectDataWithOnlyLenghtCheck(String typeOfData, int minLenght) {
        String data;
        while (true) {
            System.out.println("Type " + typeOfData);
            data = choice.getStringChoice();
            if (!checkDataToRegister.stringLengthError(data, minLenght)) {
                return data;
            }
        }
    }

    public void collectLogin() {
        RegisterSqlConnection sqlRegister = new RegisterSqlConnection();
        String loginToCheck = "";
        while (true) {
            System.out.println("Type login");
            loginToCheck = choice.getStringChoice();
            if (!checkDataToRegister.stringLengthError(loginToCheck, 6) && !sqlRegister.ifUserExist(loginToCheck)) {
                user.setLogin(loginToCheck);
                return;
            }
        }
    }

    public void collectPassword() {
        String password = collectDataWithOnlyLenghtCheck("Password", 8);
        user.setPassword(password);
    }

    public void collectEmail() {
        String emailToCheck = "";
        while (true) {
            System.out.println("Type email");
            emailToCheck = choice.getStringChoice();
            if (!checkDataToRegister.stringLengthError(emailToCheck, 8) && checkDataToRegister.stringContinesSymbol(emailToCheck, '@')) {
                user.setEmail(emailToCheck);
                return;
            }
        }
    }

    public void collectFirstName() {
        String firstName = collectDataWithOnlyLenghtCheck("First Name", 3);
        user.setFirstName(firstName);

    }

    public void collectLastName() {
        String lastName = collectDataWithOnlyLenghtCheck("Last Name", 3);
        user.setLastName(lastName);

    }

    public void collectSex() {
        String sexToCheck;
        while (true) {
            System.out.println("Type sex (M or F)");
            sexToCheck = choice.getStringChoice();
            if (sexToCheck.length() == 1 && (sexToCheck.equals("M") || sexToCheck.equals("F"))) {
                user.setSex(sexToCheck);
                return;
            } else {
                System.out.println("Type correct character.");
            }
        }
    }

    public void collectDataOfBirth() {
        Pattern dateOfBirthPattern = Pattern.compile("(19\\d\\d|20[0-1]\\d|202[0-4])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])");
        String dateOfBirthToCheck;
        while (true) {
            System.out.println("Type date of birth (YYYY-MM-DD):");
            dateOfBirthToCheck = choice.getStringChoice();
            if (dateOfBirthPattern.matcher(dateOfBirthToCheck).matches()) {
                user.setDateOfBirth(dateOfBirthToCheck);
                return;
            } else {
                System.out.println("Invalid date. Please use YYYY-MM-DD.");
            }
        }
    }

    public void collectPhoneNumber() {
        RegisterSqlConnection sqlRegister = new RegisterSqlConnection();
        int phoneNumberToCheck;
        while (true) {
            System.out.println("Type phone number: ");
            phoneNumberToCheck = choice.getIntChoice();
            if (phoneNumberToCheck >= 100000000 && phoneNumberToCheck <= 999999999) {
                user.setPhoneNumber(phoneNumberToCheck);
                return;
            } else {
                System.out.println("Wrong phone number. Try again");
            }
        }
    }
}
