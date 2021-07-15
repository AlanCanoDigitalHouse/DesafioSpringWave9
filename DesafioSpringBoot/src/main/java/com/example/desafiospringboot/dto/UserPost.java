package com.example.desafiospringboot.dto;

import org.json.simple.JSONArray;

public class UserPost {
    private int userId;
    private JSONArray posts;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public JSONArray getPosts() {
        return posts;
    }
    public void setPosts(JSONArray posts) {
        this.posts = posts;
    }
       
}
