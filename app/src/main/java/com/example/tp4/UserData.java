package com.example.tp4;

public class UserData {
    private static final UserData instance = new UserData();
    private String userEmail;

    private UserData() {}

    public static UserData getInstance() {
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
