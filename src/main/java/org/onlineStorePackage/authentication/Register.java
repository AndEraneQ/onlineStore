package org.onlineStorePackage.authentication;
import org.onlineStorePackage.menu.Menu;
import org.onlineStorePackage.SQL.SqlRegister;
import java.sql.SQLException;
import java.util.Scanner;

public class Register extends CollectDataToRegister{
    private void registerCollectData(){
        collectLogin();
        collectPassword();
        collectEmail();
        collectFirstName();
        collectLastName();
        collectSex();
        collectDataOfBirth();
        collectPhoneNumber();
    }
    public void menuAfterRegister(){
        Menu postRegisterMenu = new Menu();
        postRegisterMenu.run();
    }
    public void addUserToDataBase(){
        SqlRegister sqlRegister = new SqlRegister();
        if(sqlRegister.addUserToDatabase(user)){
            System.out.println("Registered successfully.");
        }
        else{
            System.out.println("Mistake of registration.");
        }
    }
    public void run(){
        registerCollectData();
        addUserToDataBase();
        menuAfterRegister();
    }
}
