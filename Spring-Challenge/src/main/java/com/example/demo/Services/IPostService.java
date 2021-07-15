package com.example.demo.Services;

import com.example.demo.DTOs.FollowedPostsDTO;
import com.example.demo.DTOs.PostDTO;
import com.example.demo.DTOs.PostsDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;


public interface IPostService {

    void createPost(PostDTO post) throws CustomExceptionHandler;
    PostsDTO getPostsByUserId(int userId) throws CustomExceptionHandler;
    FollowedPostsDTO getFollowedPosts(int userId, String order, int daysBefore) throws CustomExceptionHandler;

}
