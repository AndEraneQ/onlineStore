package org.onlineStorePackage.users;
public class User extends PersonData{
    public User(String login,String password,String email,String firstName,String lastName,String sex,String dateOfBirth, int phoneNumber){
        this.login=login;
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.sex=sex;
        this.dateOfBirth=dateOfBirth;
        this.phoneNumber=phoneNumber;
    }
    public User(){
        this.login=null;
        this.password=null;
        this.email=null;
        this.firstName=null;
        this.lastName=null;
        this.sex=null;
        this.dateOfBirth=null;
        this.phoneNumber=0;
    }
}
