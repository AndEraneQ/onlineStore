package pl.onlineStore.users;

public class Person {
    protected String login;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String sex;
    protected String dateOfBirth;
    protected int phoneNumber;
    protected String typeOfUser;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Person(String login, String password, String email, String firstName, String lastName, String sex, String dateOfBirth, int phoneNumber, String typeOfUser) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.typeOfUser = typeOfUser;

    }

    public Person() {
        this.login = null;
        this.password = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.sex = null;
        this.dateOfBirth = null;
        this.phoneNumber = 0;
    }
    public void printYourData(){
        System.out.println("Login: " + login);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Sex: " + sex);
        System.out.println("Date of birth: " + dateOfBirth);
        System.out.println("Phone Number: " + phoneNumber);
    }
}
