package com.example.socialmeli.models;

import com.example.socialmeli.dtos.responses.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class User {
    Integer id;
    String name;
    ArrayList<User> followers;
    ArrayList<User> followed;
    ArrayList<ResponsePostDto> posts;

    public User(Integer id, String name){
        this.id = id;
        this.name = name;
        followed = new ArrayList<>();
        followers = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public void addFollowed(User user) {
        if(!followed.contains(user))
            followed.add(user);
    }

    public void addFollower(User user) {
        if(!followers.contains(user))
            followers.add(user);
    }

    public void removeFollowed(User userToUnfollow) {
            followed.remove(userToUnfollow);
    }

    public void removeFollower(User user) {
            followers.remove(user);
    }


    public void addPost(ResponsePostDto responsePostDto) {
        posts.add(responsePostDto);
    }



}
