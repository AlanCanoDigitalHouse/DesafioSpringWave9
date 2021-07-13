package com.example.desafiospring.utils.importsmodels;

public class User {
    private long userId;
    private String userName;

    public long getUserID() {
        return userId;
    }

    public void setUserId(long value) {
        this.userId = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String value) {
        this.userName = value;
    }
}
