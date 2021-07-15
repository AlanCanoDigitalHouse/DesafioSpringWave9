package com.example.demo.models;

import com.example.demo.dtos.PostDTO;
import com.example.demo.dtos.UserDTO;

import java.util.List;

public class User {

    private Integer userId;
    private String userName;
    private List<UserDTO> followers;
    private List<UserDTO> followed;
    private List<PostDTO> posts;

    public User() {
    }

    public User(Integer userId, String userName, List<UserDTO> followers, List<UserDTO> followed, List<PostDTO> posts) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
        this.followed = followed;
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

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }

    public List<UserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserDTO> followed) {
        this.followed = followed;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", followers=" + followers +
                ", followed=" + followed +
                ", posts=" + posts +
                '}';
    }
}
