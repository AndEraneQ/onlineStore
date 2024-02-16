package pl.onlineStore.UserActions;
import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.ItemsInShop.ManageItems;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.users.User;
import pl.onlineStore.ItemsInShop.Item;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class GoToTheShopAction implements DataToConnectToSql {
    User user = UserDataSingleton.getInstance().getUser();
    private Choice choice = new Choice();
    private ManageItems manageItems = new ManageItems();
    public void addNewProductToYourCart(){
        CollectDataForItems collectDataForItems = new CollectDataForItems();
        String categoryOfProduct = collectDataForItems.collectCategory();
        ArrayList<Item> listOfItemsInCategory = new ArrayList<>(manageItems.collectListOfItemsNamesFromCategory(categoryOfProduct));
        Item dataOfItemChoosenbyUser = null;
        while(true){
            System.out.println("Choose name of product from list bellow");
            for(Item item : listOfItemsInCategory){
                System.out.println("Name: " + item.getName() + ", quantity: " + item.getQuantity() + ", price: " + item.getPrice());
            }
            String nameOfProductChooseByUser = choice.getStringChoice();
            for (Item item : listOfItemsInCategory) {
                if(item.getName().equals(nameOfProductChooseByUser)){
                    dataOfItemChoosenbyUser = new Item(item);
                    break;
                }
            }
            if(dataOfItemChoosenbyUser!=null){
                break;
            }
        }
        int howMuchUserWantToBuy;
        while(true) {
            System.out.println("Type how much you want buy (" + dataOfItemChoosenbyUser.getQuantity() + " possible to buy)");
            howMuchUserWantToBuy = choice.getIntChoice();
            if(dataOfItemChoosenbyUser.getQuantity()>=howMuchUserWantToBuy && howMuchUserWantToBuy>=0){
                dataOfItemChoosenbyUser.setQuantity(howMuchUserWantToBuy);
                break;
            }
            System.out.println("Wrong number. Try again");
        }
        manageItems.addOrDeleteQuantityOfProductsToDatabase(dataOfItemChoosenbyUser.getName(),howMuchUserWantToBuy,'-');
        user.addElementToList(dataOfItemChoosenbyUser);
    }
    public void checkYourCart(){
        if(user.getShoppingList()==null){
            System.out.println("Your shopping list is empty.");
            return;
        }
        Double sumOfPriceForAllCart = 0.0, sumOfPriceForOneNameItems;
        for(Item item : user.getShoppingList()){
            sumOfPriceForOneNameItems = item.getPrice() * item.getQuantity();
            System.out.println("Category: " + item.getCategory() + ", name: " + item.getName() + ", how much: " + item.getQuantity() + ", price for one: " + item.getPrice() + ", sum: " + sumOfPriceForOneNameItems);
            sumOfPriceForAllCart += sumOfPriceForOneNameItems;
        }
        System.out.println("You will pay for everything: " + sumOfPriceForAllCart);
    }
    public void buyProductsFromCart() {
        if (user.getShoppingList().size() == 0) {
            System.out.println("Your cart is empty.");
            return;
        }
        try {
            UserManageBudgetAction userManageBudgetAction = new UserManageBudgetAction();
            double accountBalance = userManageBudgetAction.checkMoneyBalance();
            Double sumOfPriceForAllCart =0.0;
            for (Item item : user.getShoppingList()) {
                sumOfPriceForAllCart += item.getPrice() * item.getQuantity();
            }
            if (accountBalance < sumOfPriceForAllCart) {
                Double howMuchMoneyMissing = accountBalance - sumOfPriceForAllCart;
                System.out.println("Your balance is " + accountBalance + ". You need to pay for all " + sumOfPriceForAllCart +
                        " so it's necessary deposit minimum " + howMuchMoneyMissing + " money to buy a product");
            } else {
                userManageBudgetAction.depositOrWithdrawMoney('-',sumOfPriceForAllCart);
                for (Item item : user.getShoppingList()) {
                    Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
                    String sql2 = "INSERT INTO purchaseHistory VALUES (?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql2);
                    statement = connection.prepareStatement(sql2);
                    statement.setString(1, user.getLogin());
                    statement.setInt(2, item.getQuantity());
                    statement.setDouble(3, sumOfPriceForAllCart);
                    statement.setString(4, item.getName());
                    statement.executeUpdate();
                }
                System.out.println("You bought your items!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.clearShoppingList();
    }
    public void deleteItemFromYourCart() {
        Item itemToDelete = manageItems.collectItemFromList(user.getShoppingList());
        int howMuchDelete = 0;
        while(true) {
            System.out.println("How much of " + itemToDelete.getName() + " you want delete (You have now" + itemToDelete.getQuantity() + ")");
            howMuchDelete = choice.getIntChoice();
            if (howMuchDelete >= 0 && howMuchDelete <= itemToDelete.getQuantity()) {
                break;
            }
        }
        ArrayList<Item> newShoppingList= new ArrayList<>(user.getShoppingList());
        Iterator<Item> iterator = newShoppingList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName().equals(itemToDelete.getName())) {
                item.setQuantity(item.getQuantity() - howMuchDelete);
                if (item.getQuantity() == 0) {
                    iterator.remove();
                }
            }
        }
        user.setShoppingList(newShoppingList);
        manageItems.addOrDeleteQuantityOfProductsToDatabase(itemToDelete.getName(),howMuchDelete,'+');
    }
}
