package com.example.socialmeli.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User>{

    private Integer userId;
    private String userName;
    private List<User> followers;
    private List<User> followed;
    private List<Post> posts;
    private List<Post> postsPromo;

    public User(String userName){
        this.userName = userName;
        this.followers = new ArrayList<>();
        this.followed = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.postsPromo = new ArrayList<>();
    }

    public User(Integer userId, String userName){
        this.userId = userId;
        this.userName = userName;
        this.followers = new ArrayList<>();
        this.followed = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.postsPromo = new ArrayList<>();
    }

    @Override
    public int compareTo(User user) {
        return userName.compareTo(user.getUserName());
    }
}
