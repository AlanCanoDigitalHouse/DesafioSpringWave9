package com.springChallenge.SpringChallenge.Repositories;

import com.springChallenge.SpringChallenge.Dtos.Post;
import com.springChallenge.SpringChallenge.Dtos.Seller;
import com.springChallenge.SpringChallenge.Dtos.Shopper;

import java.util.List;

public interface IRepository {
    void follow(int userId, int userIdToFollow);
    Seller getSellerById(int userId);
    Shopper getSellersByShopperId(int userId, String order);
    void addProduct(Post post);
    boolean existUser(int userId);
    List<Post> followedPost(int userId, String sort);
    void unfollow(int userId, int userIdToUnfollow);
}
