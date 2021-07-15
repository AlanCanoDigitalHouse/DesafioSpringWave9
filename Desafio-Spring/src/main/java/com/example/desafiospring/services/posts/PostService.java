package com.example.desafiospring.services.posts;

import com.example.desafiospring.dtos.Post;
import com.example.desafiospring.dtos.UserPosts;
import com.example.desafiospring.exceptions.*;


public interface PostService {

    void createPost(Post post) throws UserNotExistException, NeedDiscountException;
    public void createPromoPost(Post post) throws UserNotExistException, NeedDiscountException, HasPromoException, DontNeedDiscountException;
    UserPosts findPostsByUserId (Integer userId, String order) throws UserNotExistException, UserDontHavePostsException, UserDontHaveFollowersorFollowed;
    public UserPosts getOrderedPosts (UserPosts userPosts, String order);
    public UserPosts countPromo (Integer userId) throws UserNotExistException, UserDontHavePostsException;
    public UserPosts getPosts (Integer userId) throws UserDontHavePostsException, UserNotExistException, NotHavePromoProducts;

}
