package com.example.demo.Entities;

import lombok.Data;

import java.util.*;

@Data
public class User {
    private int userid;
    private String userName;
    private Map<Integer,User> userFollowers;
    private Map<Integer,User> userFollow;
    private Map<Integer,Post> userPost;

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", userName='" + userName + '\'' +
                '}';
    }

    public User(int userid, String userName) {
        this.userid = userid;
        this.userName = userName;
        this.userFollowers = new HashMap<>();
        this.userFollow = new HashMap<>();
        this.userPost = new HashMap<>();
    }

}
