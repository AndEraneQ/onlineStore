package pl.onlineStore.users;

public class Admin extends Person{
    public Admin(String login,String password,String email,String firstName,String lastName,String sex,String dateOfBirth, int phoneNumber){
        this.login=login;
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.sex=sex;
        this.dateOfBirth=dateOfBirth;
        this.phoneNumber=phoneNumber;
    }
    public Admin(){
        this.login=null;
        this.password=null;
        this.email=null;
        this.firstName=null;
        this.lastName=null;
        this.sex=null;
        this.dateOfBirth=null;
        this.phoneNumber=0;
    }
    public Admin(Person p1){
        this.login=p1.login;
        this.password=p1.password;
        this.email=p1.email;
        this.firstName=p1.firstName;
        this.lastName=p1.lastName;
        this.sex=p1.sex;
        this.phoneNumber=p1.phoneNumber;
        this.typeOfUser=p1.typeOfUser;
    }
}
