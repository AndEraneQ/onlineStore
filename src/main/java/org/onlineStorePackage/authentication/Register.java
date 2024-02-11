package org.onlineStorePackage.authentication;
import org.onlineStorePackage.menu.StartingMenu;
import org.onlineStorePackage.SQL.SqlRegister;

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
        StartingMenu postRegisterMenu = new StartingMenu();
        postRegisterMenu.run();
    }
    public void addUserToDataBase(){
        SqlRegister sqlRegister = new SqlRegister();
        if(sqlRegister.userDataBaseAddUser(user) && sqlRegister.accountBalanceDataBaseAddUser(user)){
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
