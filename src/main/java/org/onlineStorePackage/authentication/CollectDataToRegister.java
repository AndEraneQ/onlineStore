package org.onlineStorePackage.authentication;
import org.onlineStorePackage.SQL.SqlRegister;
import org.onlineStorePackage.users.User;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class CollectDataToRegister {
    private Scanner scanner = new Scanner(System.in);
    private CheckDataToRegister checkDataToRegister = new CheckDataToRegister();
    protected User user = new User();
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
        SqlRegister sqlRegister = new SqlRegister();
        boolean loginFreeAndCorrect = false;
        while(!loginFreeAndCorrect){
            System.out.println("Type login");
            String loginToCheck = scanner.nextLine();
            if(!checkDataToRegister.stringLengthError(loginToCheck,6) && !sqlRegister.dataExistError(loginToCheck)){
                user.setLogin(loginToCheck);
                loginFreeAndCorrect=true;
            }
        }
    }
    public void collectPassword(){
        user.setPassword(collectDataWithOnlyLenghtCheck("Password",8));
    }
    public void collectEmail(){
        boolean emailIsCorrect = false;
        while(!emailIsCorrect){
            System.out.println("Type email");
            String emailToCheck = scanner.nextLine();
            if(!checkDataToRegister.stringLengthError(emailToCheck,8) && checkDataToRegister.stringContinesSymbol(emailToCheck,'@')){
                emailIsCorrect=true;
                user.setEmail(emailToCheck);
            }
        }
    }
    public void collectFirstName() {
        user.setFirstName(collectDataWithOnlyLenghtCheck("First Name",3));
    }
    public void collectLastName() {
        user.setLastName(collectDataWithOnlyLenghtCheck("Last Name", 3));
    }
    public void collectSex(){
        boolean sexIsCorrect = false;
        while(!sexIsCorrect){
            System.out.println("Type sex (M or F)");
            String sexToCheck = scanner.nextLine();
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
        while (!dateOfBirthIsCorrect) {
            System.out.println("Type date of birth (YYYY-MM-DD):");
            String dateOfBirthToCheck = scanner.nextLine();
            if (dateOfBirthPattern.matcher(dateOfBirthToCheck).matches()) {
                user.setDateOfBirth(dateOfBirthToCheck);
                dateOfBirthIsCorrect = true;
            } else {
                System.out.println("Invalid date. Please use YYYY-MM-DD.");
            }
        }
    }
    public void collectPhoneNumber(){
        SqlRegister sqlRegister = new SqlRegister();
        boolean phoneNumberIsCorrect = false;
        while(!phoneNumberIsCorrect){
            System.out.println("Type phone number: ");
            try {
                int phoneNumberToCheck = scanner.nextInt();
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
