package pl.onlineStore.menu;

import pl.onlineStore.AdminActions.AddOrDeleteAdminLaws;
import pl.onlineStore.AdminActions.AddOrDeleteItems;
import pl.onlineStore.AdminActions.AddOrDeleteShoppingCategory;
import pl.onlineStore.AdminActions.EditItemsDataAction;
import pl.onlineStore.choices.Choice;

public class AdminChoicesHandler {
    private Choice choice = new Choice();

    private void printAddOrDeleteMenu(String specify) {
        System.out.println("1 - Add " + specify);
        System.out.println("2 - Delete " + specify);
        System.out.println("3 - Back");
    }

    public void addOrDeleteItemsInShop() {
        int userChoice = 0;
        while (true) {
            userChoice = choice.getIntChoice();
            AddOrDeleteItems addOrDeleteItemsAction = new AddOrDeleteItems();
            switch (userChoice) {
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
                    System.out.println("Invalid userChoice. Try again");
                    break;
            }
        }
    }

    public void addOrDeleteAdminRights() {
        int userChoice;
        while (true) {
            printAddOrDeleteMenu("admin laws");
            userChoice = choice.getIntChoice();
            AddOrDeleteAdminLaws addOrDeleteAdminRightsAction = new AddOrDeleteAdminLaws();
            switch (userChoice) {
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
                    System.out.println("Invalid userChoice. Try again");
                    break;
            }
        }
    }

    public void editItemsDataInShop() {
        EditItemsDataAction editItemsDataAction = new EditItemsDataAction();
        editItemsDataAction.editItemsRun();
    }

    public void AddOrDeleteNewCategoryInShop() {
        int userChoice;
        while (true) {
            printAddOrDeleteMenu("category");
            userChoice = choice.getIntChoice();
            AddOrDeleteShoppingCategory addOrDeleteShoppingCategory = new AddOrDeleteShoppingCategory();
            switch (userChoice) {
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
                    break;
            }
        }
    }
}
