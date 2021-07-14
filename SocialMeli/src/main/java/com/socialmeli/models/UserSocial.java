package com.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UserSocial extends User{

    private ArrayList<User> followers;
    private ArrayList<User> followed;
    private ArrayList<PostSocial> post;

    public UserSocial(int id, String name) {
        super(id, name);
        this.followers = new ArrayList<>();
        this.followed = new ArrayList<>();
        this.post = new ArrayList<>();
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowed() {
        return followed;
    }

    public void setFollowed(ArrayList<User> followed) {
        this.followed = followed;
    }

    public ArrayList<PostSocial> getPost() {
        return post;
    }

    public void setPost(ArrayList<PostSocial> post) {
        this.post = post;
    }
}
