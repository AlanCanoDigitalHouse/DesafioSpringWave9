package com.example.desafiospringboot.dto;

import org.json.simple.JSONArray;

public class FollowersDetail {
    public int userId;
    public String userName;
    public JSONArray followers;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public JSONArray getFollowers() {
        return followers;
    }
    public void setFollowers(JSONArray followers) {
        this.followers = followers;
    }
    
}
