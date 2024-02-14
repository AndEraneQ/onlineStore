package pl.onlineStore.users;

public class Admin extends Person{
    public Admin(String login,String password,String email,String firstName,String lastName,String sex,String dateOfBirth, int phoneNumber){
        super(login,password,email,firstName,lastName,sex,dateOfBirth,phoneNumber,"admin");
    }
    public Admin(){
        super(null,null,null,null,null,null,null,0,"admin");
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
