package pl.onlineStore.menu;

import pl.onlineStore.AdminActions.AddOrDeleteAdminLaws;
import pl.onlineStore.AdminActions.AddOrDeleteItems;
import pl.onlineStore.AdminActions.AddOrDeleteShoppingCategory;
import pl.onlineStore.AdminActions.EditItemsDataAction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminChoicesHandler {
    private Scanner scanner = new Scanner(System.in);
    private void printAddOrDeleteMenu(String specify){
        System.out.println("1 - Add " + specify);
        System.out.println("2 - Delete " + specify);
        System.out.println("3 - Back");
    }
    public void addOrDeleteAdminRights() {
            int choice = 0;
            boolean choiceIsCorrect;
            do {
                choiceIsCorrect = true;
                printAddOrDeleteMenu("admin laws");
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You need to write a number! Please try again");
                    choiceIsCorrect = false;
                    scanner.nextLine();
                }
                if (choiceIsCorrect) {
                    AddOrDeleteAdminLaws addOrDeleteAdminRightsAction = new AddOrDeleteAdminLaws();
                    switch (choice) {
                        case 1:
                            addOrDeleteAdminRightsAction.addAdminLaws();
                            break;
                        case 2:
                            addOrDeleteAdminRightsAction.deleteAdminLaws();
                            break;
                        case 3:
                            System.out.println("Backing to menu.");
                            return;
                        default:
                            System.out.println("Invalid choice. Try again");
                            choiceIsCorrect =false;
                            break;
                    }
                }
            } while (!choiceIsCorrect || choice!=3) ;
        }
        public void addOrDeleteItemsInShop(){
            int choice = 0;
            boolean choiceIsCorrect;
            do {
                choiceIsCorrect = true;
                printAddOrDeleteMenu("item");
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You need to write a number! Please try again");
                    choiceIsCorrect = false;
                    scanner.nextLine();
                }
                if (choiceIsCorrect) {
                    AddOrDeleteItems addOrDeleteItemsAction = new AddOrDeleteItems();
                    switch (choice) {
                        case 1:
                            addOrDeleteItemsAction.addItem();
                            break;
                        case 2:
                            addOrDeleteItemsAction.removeItem();
                            break;
                        case 3:
                            System.out.println("Backing to menu.");
                            return;
                        default:
                            System.out.println("Invalid choice. Try again");
                            choiceIsCorrect =false;
                            break;
                    }
                }
            } while (!choiceIsCorrect || choice!=3) ;
        }
        public void addOrSubstractExistingItemInShop(){

        }
        public void editItemsDataInShop(){
            EditItemsDataAction editItemsDataAction = new EditItemsDataAction();
            editItemsDataAction.editItemsRun();
        }
        public void AddOrDeleteNewCategoryInShop() {
            int choice = 0;
            boolean choiceIsCorrect;
            do {
                choiceIsCorrect = true;
                printAddOrDeleteMenu("category");
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You need to write a number! Please try again");
                    choiceIsCorrect = false;
                    scanner.nextLine();
                }
                if (choiceIsCorrect) {
                    AddOrDeleteShoppingCategory addOrDeleteShoppingCategory = new AddOrDeleteShoppingCategory();
                    switch (choice) {
                        case 1:
                            addOrDeleteShoppingCategory.addCategory();
                            break;
                        case 2:
                            addOrDeleteShoppingCategory.removeCategory();
                            break;
                        case 3:
                            System.out.println("Backing to menu.");
                            return;
                        default:
                            System.out.println("Invalid choice. Try again");
                            choiceIsCorrect =false;
                            break;
                    }
                }
            } while (!choiceIsCorrect || choice!=3) ;
        }
}
