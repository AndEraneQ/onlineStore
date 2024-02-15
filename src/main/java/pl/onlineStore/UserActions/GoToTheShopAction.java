package pl.onlineStore.UserActions;
import pl.onlineStore.ItemsInShop.CollectDataForItems;
import pl.onlineStore.SQL.DataToConnectToSql;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.users.User;
import pl.onlineStore.ItemsInShop.Item;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class GoToTheShopAction implements DataToConnectToSql {
    User user = UserDataSingleton.getInstance().getUser();
    Scanner scanner = new Scanner(System.in);
    public void addNewProductToYourCart(){

        CollectDataForItems collectDataForItems = new CollectDataForItems();
        String categoryOfProduct = collectDataForItems.collectCategory();
        ArrayList<Item> listOfItemsInCategory = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "SELECT * FROM itemsInShop WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,categoryOfProduct);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Item item = new Item();
                item.setCategory(categoryOfProduct);
                item.setName(resultSet.getString("name"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getDouble("price"));
                listOfItemsInCategory.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        boolean productIsCorrect = false;
        Item dataOfItemChoosenbyUser = null;
        do {
            System.out.println("Choose name of product from list bellow");
            for(Item item : listOfItemsInCategory){
                System.out.println("Name: " + item.getName() + ", quantity: " + item.getQuantity() + ", price: " + item.getPrice());
            }
            String nameOfProductChoosenByUser = scanner.nextLine();
            for (Item item : listOfItemsInCategory) {
                if(item.getName().equals(nameOfProductChoosenByUser)){
                    dataOfItemChoosenbyUser = new Item(item);
                    productIsCorrect = true;
                    break;
                }
            }
        } while(!productIsCorrect);
        int howMuchUserWantToBuy=0;
        while(true) {
            System.out.println("Type how much you want buy (" + dataOfItemChoosenbyUser.getQuantity() + " possible to buy)");
            howMuchUserWantToBuy = scanner.nextInt();
            if(dataOfItemChoosenbyUser.getQuantity()>=howMuchUserWantToBuy && howMuchUserWantToBuy>=0){
                dataOfItemChoosenbyUser.setQuantity(howMuchUserWantToBuy);
                break;
            }
            System.out.println("Wrong number. Try again");
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql2 = "UPDATE itemsInShop SET quantity = quantity - ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql2);
            statement.setInt(1,howMuchUserWantToBuy);
            statement.setString(2,dataOfItemChoosenbyUser.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.addElementToList(dataOfItemChoosenbyUser);
    }
    public void checkYourCart(){
        Double sumOfPriceForAllCart = 0.0, sumOfPriceForOneNameItems;
        for(Item item : user.getShoppingList()){
            sumOfPriceForOneNameItems = item.getPrice() * item.getQuantity();
            System.out.println("Category: " + item.getCategory() + ", name: " + item.getName() + ", how much: " + item.getQuantity() + ", price for one: " + item.getPrice() + ", sum: " + sumOfPriceForOneNameItems);
            sumOfPriceForAllCart += sumOfPriceForOneNameItems;
        }
        System.out.println("You will pay for everything: " + sumOfPriceForAllCart);
    }
    public void buyProductsFromCart() {
        try {
            if (user.getShoppingList().size() == 0) {
                System.out.println("Your cart is empty.");
                return;
            }
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String sql = "SELECT balance FROM accountBalance WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            double accountBalance = 0.0;
            if (resultSet.next()) {
                accountBalance = resultSet.getDouble("balance");
            }
            Double sumOfPriceForAllCart = 0.0;
            for (Item item : user.getShoppingList()) {
                sumOfPriceForAllCart += item.getPrice() * item.getQuantity();
            }
            if (accountBalance < sumOfPriceForAllCart) {
                Double howMuchMoneyMissing = accountBalance - sumOfPriceForAllCart;
                System.out.println("Your balance is " + accountBalance + ". You need to pay for all " + sumOfPriceForAllCart +
                        " so it's necessary deposit minimum " + howMuchMoneyMissing + " money to buy a product");
            } else {
                String sql3 = "UPDATE accountBalance SET balance = balance - ? WHERE login = ?";
                statement = connection.prepareStatement(sql3);
                statement.setDouble(1,sumOfPriceForAllCart);
                statement.setString(2,user.getLogin());
                statement.executeUpdate();
                System.out.println("- " + sumOfPriceForAllCart + " money from your account.");
                for (Item item : user.getShoppingList()) {
                    String sql2 = "INSERT INTO purchaseHistory VALUES (?,?,?,?)";
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
        String itemToDelete;
        Item itemDataDeletor = null;
        while (true) {
            System.out.println("Choose a product from list bellow");
            for (Item item : user.getShoppingList()) {
                System.out.print(item.getName() + " ");
            }
            itemToDelete = scanner.nextLine();
            for (Item item : user.getShoppingList()) {
                if (itemToDelete.equals(item.getName())) {
                    itemDataDeletor = new Item(item);
                    break;
                }
            }
            if (itemDataDeletor != null) {
                break;
            }
        }
        boolean choiceIsCorrect;
        int howMuchDelete = 0;
        do {
            choiceIsCorrect = true;
            System.out.println("How much of " + itemDataDeletor.getName() + " you want delete (You have now" + itemDataDeletor.getQuantity() + ")");
            try {
                howMuchDelete = scanner.nextInt();
                if (howMuchDelete < 0 || howMuchDelete > itemDataDeletor.getQuantity()) {
                    choiceIsCorrect = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to write a number! Please try again");
                choiceIsCorrect = false;
                scanner.nextLine();
            }
        } while (!choiceIsCorrect);
        ArrayList<Item> newShoppingList= new ArrayList<>(user.getShoppingList());
        Iterator<Item> iterator = newShoppingList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName().equals(itemDataDeletor.getName())) {
                item.setQuantity(item.getQuantity() - howMuchDelete);
                if (item.getQuantity() == 0) {
                    iterator.remove();
                }
            }
        }
        user.setShoppingList(newShoppingList);
        try {
            Connection connection = DriverManager.getConnection(url,sqlUsername,sqlPassword);
            String sql = "UPDATE itemsInShop SET quantity = quantity + ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,howMuchDelete);
            statement.setString(2,itemToDelete);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
