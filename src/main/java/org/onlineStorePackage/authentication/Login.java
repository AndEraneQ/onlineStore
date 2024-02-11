package org.onlineStorePackage.authentication;
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
        scanner.close();
    }
    public void run(){
        loginCollectData();
        SqlLogin connection = new SqlLogin();
        if(connection.correctLoginAndPasswordCheck(login,password) && connection.getTypeOfUser().equals("admin")){
            System.out.println("tak");
        }
        else if(connection.correctLoginAndPasswordCheck(login,password) && connection.getTypeOfUser().equals("user")){
            System.out.println(("TakTak"));
        }
        else{
            System.out.println("Nie");
        }
    }
}
