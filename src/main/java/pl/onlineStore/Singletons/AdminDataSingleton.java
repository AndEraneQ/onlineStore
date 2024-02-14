package pl.onlineStore.Singletons;

import pl.onlineStore.users.Admin;

public class AdminDataSingleton {
    private static AdminDataSingleton instance;
    private Admin admin;
    private AdminDataSingleton() {}
    public static AdminDataSingleton getInstance() {
        if (instance == null) {
            instance = new AdminDataSingleton();
        }
        return instance;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public Admin getAdmin() {
        return admin;
    }
}
