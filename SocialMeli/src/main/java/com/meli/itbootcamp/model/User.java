package com.meli.itbootcamp.model;

public abstract class User {

    private Integer userID;
    private String userName;

    public User(Integer userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  User){
            return this.userID.equals(((User) obj).getUserID());
        }
        return  false;
    }

    @Override
    public String toString() {
        return "User: " + this.userName + "-"+ "UserID: " + this.userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
