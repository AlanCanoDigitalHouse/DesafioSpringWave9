package com.desafio.demo.entiity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private String name;
    private List<Integer> followed;
    private List<Integer> followers;


    public User(String name) {
        this.name = name;
        this.followed= new ArrayList<>();
        this.followers=new ArrayList<>();
    }

    public void addFollower(Integer userId) {
        followers.add(userId);
    }

    public void addFollowed(Integer userIdToFollow) {
        followed.add(userIdToFollow);
    }

    public void deleteFollowed(Integer userId){followed.remove(userId);}

    public void deleteFollower(Integer userId){followers.remove(userId);}

}
