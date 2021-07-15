package com.example.desafiospring.services.posts;

import com.example.desafiospring.dtos.Post;
import com.example.desafiospring.dtos.UserPosts;
import com.example.desafiospring.exceptions.UserDontHaveFollowersorFollowed;
import com.example.desafiospring.exceptions.UserDontHavePosts;
import com.example.desafiospring.exceptions.UserNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    void createPost(Post post) throws UserNotExistException;
    UserPosts findPostsByUserId (Integer userId, String order) throws UserNotExistException, UserDontHavePosts, UserDontHaveFollowersorFollowed;
    public UserPosts getOrderedPosts (UserPosts userPosts, String order);
    public UserPosts countPromo (Integer userId) throws UserNotExistException, UserDontHavePosts;
    public UserPosts getPosts (Integer userId) throws UserDontHavePosts, UserNotExistException;

}
