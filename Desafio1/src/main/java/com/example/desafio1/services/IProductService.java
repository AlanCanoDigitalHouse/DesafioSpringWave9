package com.example.desafio1.services;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;

import java.util.Date;
import java.util.List;

public interface IProductService {
    public void addNewPost(Post post);
    public List<Post> listLastFollowedPosts(List<User> vendors);
}
