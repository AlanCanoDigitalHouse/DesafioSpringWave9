package com.example.desafiospring.models;

public class User {
    protected String userName;
    protected Long userId;

    public User() {
    }

    public User(String userName, Long userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
