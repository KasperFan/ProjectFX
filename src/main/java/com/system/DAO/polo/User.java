package com.system.DAO.polo;

import com.system.DAO.utils.SHA256;

public class User {
    private int id = 0;
    private String name = "null";
    private String password = "null";
    private boolean isAdmin = false;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, String password, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.setPassword(password);
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SHA256.getSHA256(password);
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}