package com.mercadolibre.desafio_spring.entities;

import com.mercadolibre.desafio_spring.dtos.request.UserProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class User {
    Integer userId;
    String userName;
    ArrayList<User> followers;
    ArrayList<User> following;
    ArrayList<Post> posts;

    public User(Integer userId, String userName, ArrayList<User> followers, ArrayList<User> following, ArrayList<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}

