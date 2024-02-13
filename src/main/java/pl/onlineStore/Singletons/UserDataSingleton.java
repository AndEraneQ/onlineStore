package pl.onlineStore.Singletons;

import pl.onlineStore.users.User;

public class UserDataSingleton {
    private static UserDataSingleton instance;
    private User user;

    private UserDataSingleton() {}
    public static UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
