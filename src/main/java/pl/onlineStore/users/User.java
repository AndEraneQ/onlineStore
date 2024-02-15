package pl.onlineStore.users;

import java.util.ArrayList;
import pl.onlineStore.ItemsInShop.Item;

public class User extends Person {
    private ArrayList<Item> shoppingList = new ArrayList<>();
    public User(String login,String password,String email,String firstName,String lastName,String sex,String dateOfBirth, int phoneNumber){
        super(login,password,email,firstName,lastName,sex,dateOfBirth,phoneNumber,"user");
    }
    public User(){
        super(null,null,null,null,null,null,null,0,"user");
    }
    public User(Person p1){
        this.login=p1.login;
        this.password=p1.password;
        this.email=p1.email;
        this.firstName=p1.firstName;
        this.lastName=p1.lastName;
        this.sex=p1.sex;
        this.phoneNumber=p1.phoneNumber;
        this.typeOfUser=p1.typeOfUser;
        this.dateOfBirth=p1.dateOfBirth;
    }

    public ArrayList<Item> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<Item> shoppingList) {
        this.shoppingList = shoppingList;
    }
    public void addElementToList(Item item){
        shoppingList.add(item);
    }
    public void clearShoppingList(){
        shoppingList.clear();
    }
}
