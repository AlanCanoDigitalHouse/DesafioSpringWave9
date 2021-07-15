package com.example.desafio1.repositories;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;

import java.util.List;

public interface IProductRepository {
    void addNewPost(Post post);
    List<Post> listFollowedLastPosts(List<User> vendors);
}
