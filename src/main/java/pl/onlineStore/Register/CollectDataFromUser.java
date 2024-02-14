package pl.onlineStore.Register;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.User;

import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class CollectDataFromUser {
    private Scanner scanner = new Scanner(System.in);
    private CheckDataToRegister checkDataToRegister = new CheckDataToRegister();
    protected User user = UserDataSingleton.getInstance().getUser();
    public String collectDataWithOnlyLenghtCheck(String typeOfData, int minLenght){
        boolean dataIsCorrect = false;
        String data ="";
        while(!dataIsCorrect){
            System.out.println("Type " + typeOfData);
            data = scanner.nextLine();
            if(!checkDataToRegister.stringLengthError(data,minLenght)){
                dataIsCorrect=true;
            }
        }
        return data;
    }
    public void collectLogin(){
        RegisterSqlConnection sqlRegister = new RegisterSqlConnection();
        boolean loginFreeAndCorrect = false;
        String loginToCheck = "";
        while(!loginFreeAndCorrect){
            System.out.println("Type login");
            loginToCheck = scanner.nextLine();
            if(!checkDataToRegister.stringLengthError(loginToCheck,6) && !sqlRegister.ifUserExist(loginToCheck)){
                user.setLogin(loginToCheck);
                loginFreeAndCorrect=true;
            }
        }
    }
    public void collectPassword(){
        String password = collectDataWithOnlyLenghtCheck("Password",8);
        user.setPassword(password);
    }
    public String collectEmail(){
        boolean emailIsCorrect = false;
        String emailToCheck = "";
        while(!emailIsCorrect){
            System.out.println("Type email");
            emailToCheck = scanner.nextLine();
            if(!checkDataToRegister.stringLengthError(emailToCheck,8) && checkDataToRegister.stringContinesSymbol(emailToCheck,'@')){
                emailIsCorrect=true;
                user.setEmail(emailToCheck);
            }
        }
        return emailToCheck;
    }
    public void collectFirstName() {
        String firstName = collectDataWithOnlyLenghtCheck("First Name",3);
        user.setFirstName(firstName);

    }
    public void collectLastName() {
        String lastName = collectDataWithOnlyLenghtCheck("Last Name", 3);
        user.setLastName(lastName);

    }
    public void collectSex(){
        boolean sexIsCorrect = false;
        String sexToCheck = "";
        while(!sexIsCorrect){
            System.out.println("Type sex (M or F)");
            sexToCheck = scanner.nextLine();
            if(sexToCheck.length()==1 && (sexToCheck.equals("M") || sexToCheck.equals("F"))) {
                sexIsCorrect = true;
                user.setSex(sexToCheck);
            }
            else{
                System.out.println("Type correct character.");
            }
        }
    }
    public void collectDataOfBirth(){
        Pattern dateOfBirthPattern = Pattern.compile("(19\\d\\d|20[0-1]\\d|202[0-4])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])");
        boolean dateOfBirthIsCorrect = false;
        String dateOfBirthToCheck ="";
        while (!dateOfBirthIsCorrect) {
            System.out.println("Type date of birth (YYYY-MM-DD):");
            dateOfBirthToCheck = scanner.nextLine();
            if (dateOfBirthPattern.matcher(dateOfBirthToCheck).matches()) {
                user.setDateOfBirth(dateOfBirthToCheck);
                dateOfBirthIsCorrect = true;
            } else {
                System.out.println("Invalid date. Please use YYYY-MM-DD.");
            }
        }
    }
    public void collectPhoneNumber(){
        RegisterSqlConnection sqlRegister = new RegisterSqlConnection();
        boolean phoneNumberIsCorrect = false;
        int phoneNumberToCheck =0;
        while(!phoneNumberIsCorrect){
            System.out.println("Type phone number: ");
            try {
                phoneNumberToCheck = scanner.nextInt();
                scanner.nextLine();
                if(phoneNumberToCheck>=100000000 && phoneNumberToCheck<=999999999){
                    user.setPhoneNumber(phoneNumberToCheck);
                    phoneNumberIsCorrect=true;
                }
                else{
                    System.out.println("Wrong phone number. Try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }
    public void closeScanner(){
        scanner.close();
    }
}
