package com.example.demo.Services;

import com.example.demo.DTOs.FollowedPostsDTO;
import com.example.demo.DTOs.PostDTO;
import com.example.demo.DTOs.PostsDTO;
import com.example.demo.Exceptions.ExceptionHandler;


public interface IPostService {

    void createPost(PostDTO post) throws ExceptionHandler;
    PostsDTO getPostsByUserId(int userId) throws ExceptionHandler;
    FollowedPostsDTO getFollowedPosts(int userId, String order, int daysBefore) throws ExceptionHandler;

}
