package pl.onlineStore.Register;
import pl.onlineStore.menu.StartingMenu;

public class Register extends CollectDataFromUser {
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
        RegisterSqlConnection sqlRegister = new RegisterSqlConnection();
        if(sqlRegister.addUserToUserDataBase(user) && sqlRegister.addUserToAccountBalanceDataBase(user)){
            System.out.println("Registered successfully.");
        } else{
            System.out.println("Mistake of registration.");
        }
    }
    public void run(){
        registerCollectData();
        addUserToDataBase();
        menuAfterRegister();
    }
}
