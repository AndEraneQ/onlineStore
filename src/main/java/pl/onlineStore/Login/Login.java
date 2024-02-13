package pl.onlineStore.Login;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.Person;
import pl.onlineStore.menu.UserMenu;

import java.sql.SQLException;
import java.util.Scanner;

import pl.onlineStore.users.User;

public class Login {
    private String login;
    private String password;
    private void loginCollectDataMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type login: ");
        this.login = scanner.nextLine();
        System.out.println("Type password:");
        this.password = scanner.nextLine();
    }
    public void run(){
        boolean loggedIn = false;
        while(!loggedIn) {
            loginCollectDataMenu();
            LoginSqlConnection connectionToDatabase = new LoginSqlConnection();
            if(connectionToDatabase.correctLoginAndPasswordCheck(login,password)){
                loggedIn = true;
                Person person = new Person();
                try {
                    person = connectionToDatabase.CollectAllDataFromDatabase(login);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(person.getTypeOfUser().equals("user")){
                    User user = new User(person);
                    UserDataSingleton.getInstance().setUser(user);
                    UserMenu userMenu = new UserMenu();
                    userMenu.run();
                } else{
                    System.out.println("tak");
                }
            } else{
                System.out.println("Incorrect login or password. Try again");
            }
        }
    }
}
