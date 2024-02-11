package org.onlineStorePackage.authentication;
import org.onlineStorePackage.menu.UserMenu;
import java.util.Scanner;
import org.onlineStorePackage.SQL.SqlLogin;
public class Login {
    private String login;
    private String password;
    private void loginCollectData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type login: ");
        this.login = scanner.nextLine();
        System.out.println("Type password:");
        this.password = scanner.nextLine();
    }
    public void run(){
        boolean loggedIn = false;
        while(!loggedIn) {
            loginCollectData();
            SqlLogin connectionToDatabase = new SqlLogin();
            if (connectionToDatabase.correctLoginAndPasswordCheck(login, password) && connectionToDatabase.getTypeOfUser().equals("admin")) {
                loggedIn=true;
                System.out.println("tak");
            } else if (connectionToDatabase.correctLoginAndPasswordCheck(login, password) && connectionToDatabase.getTypeOfUser().equals("user")) {
                loggedIn=true;
                System.out.println(("TakTak"));
                UserMenu userMenu = new UserMenu();
                userMenu.run(login);
            } else {
                System.out.println("Incorrect login or password. Try again");
            }
        }
    }
}
