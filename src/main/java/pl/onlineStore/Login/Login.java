package pl.onlineStore.Login;
import pl.onlineStore.Singletons.AdminDataSingleton;
import pl.onlineStore.Singletons.UserDataSingleton;
import pl.onlineStore.choices.Choice;
import pl.onlineStore.menu.AdminMenu;
import pl.onlineStore.users.Admin;
import pl.onlineStore.users.Person;
import pl.onlineStore.menu.UserMenu;

import java.sql.SQLException;

import pl.onlineStore.users.User;

public class Login {
    public void run() {
        Choice choice = new Choice();
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Type login: ");
            String login = choice.getStringChoice();
            System.out.println("Type password:");
            String password = choice.getStringChoice();
            try {
                LoginSqlConnection connectionToDatabase = new LoginSqlConnection();
                if (connectionToDatabase.correctLoginAndPasswordCheck(login, password)) {
                    loggedIn = true;
                    Person person = connectionToDatabase.CollectAllDataFromDatabase(login);
                    if (person.getTypeOfUser().equals("user")) {
                        User user = new User(person);
                        UserDataSingleton.getInstance().setUser(user);
                        UserMenu userMenu = new UserMenu();
                        userMenu.run();
                    } else {
                        Admin admin = new Admin(person);
                        AdminDataSingleton.getInstance().setAdmin(admin);
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.run();
                    }
                } else {
                    System.out.println("Incorrect login or password. Try again");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

