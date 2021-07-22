package com.api.firstspringchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class Seller extends User {

    private @Getter @Setter
    List<Post> posts;

    public void addPost(Post post){
        this.posts.add(post);
    }
}
