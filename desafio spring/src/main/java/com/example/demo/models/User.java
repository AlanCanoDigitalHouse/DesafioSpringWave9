package com.example.demo.models;

import com.example.demo.dtos.PostDto;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int userId;
    private String userName;
    private List<User> followed;
    private List<User> followers;
    private List<PostDto> posts;


    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.followed = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void follow(User user){
        this.followed.add(user);
    }

    public void addFollower(User user){
        this.followers.add(user);
    }

    public void removeFollower(User user){
        this.followers.remove(user);
    }

    public void unfollow(User user){
        this.followed.remove(followed.indexOf(user));
    }

    public int countFollowers(){
        return this.followers.size();
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowed() {
        return this.followed;
    }

    public void addPost(PostDto post){
        this.posts.add(post);
        System.out.println(this.posts);
    }

    public List<PostDto> getPosts() {
        return posts;
    }
}
