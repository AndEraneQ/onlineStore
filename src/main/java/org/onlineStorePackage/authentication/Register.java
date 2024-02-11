package org.onlineStorePackage.authentication;
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
    public void run(){
        registerCollectData();
    }
}
