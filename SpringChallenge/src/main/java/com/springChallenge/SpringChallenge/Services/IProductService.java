package com.springChallenge.SpringChallenge.Services;

import com.springChallenge.SpringChallenge.Dtos.Post;

import java.util.List;

public interface IProductService {
    void addPost(Post post);
    List<Post> followedPost(int userId, String sort);
}
