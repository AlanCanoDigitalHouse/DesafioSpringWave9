package com.example.desafio1.repositories;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;

import java.util.Date;
import java.util.List;

public interface IProductRepository {
    public void addNewPost(Post post);
    public List<Post> listFollowedLastPosts(List<User> vendors);
}
