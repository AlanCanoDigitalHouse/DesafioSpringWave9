package com.example.desafiospring.models;

public class User {
    protected String userName;
    protected Integer userId;

    public User() {
    }


    public User(String userName, Integer userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
