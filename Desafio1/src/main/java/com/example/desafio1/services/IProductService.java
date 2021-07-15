package com.example.desafio1.services;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;

import java.util.List;

public interface IProductService {
    void addNewPost(Post post);
    List<Post> listLastFollowedPosts(List<User> vendors, String order);
}
