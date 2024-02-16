package pl.onlineStore.Register;
import pl.onlineStore.menu.StartingMenu;

public class Register {
    private CollectDataFromUser collectDataFromUser = new CollectDataFromUser();
    public void run(){
        registerCollectData();
        addUserToDataBase();
        menuAfterRegister();
    }
    private void registerCollectData(){
        collectDataFromUser.collectLogin();
        collectDataFromUser.collectPassword();
        collectDataFromUser.collectEmail();
        collectDataFromUser.collectFirstName();
        collectDataFromUser.collectLastName();
        collectDataFromUser.collectSex();
        collectDataFromUser.collectDataOfBirth();
        collectDataFromUser.collectPhoneNumber();
    }
    private void menuAfterRegister(){
        StartingMenu postRegisterMenu = new StartingMenu();
        postRegisterMenu.run();
    }
    private void addUserToDataBase(){
        RegisterSqlConnection sqlRegister = new RegisterSqlConnection();
        if(sqlRegister.addUserToUserDataBase(collectDataFromUser.user) && sqlRegister.addUserToAccountBalanceDataBase(collectDataFromUser.user)){
            System.out.println("Registered successfully.");
        } else{
            System.out.println("Mistake of registration.");
        }
    }
}
