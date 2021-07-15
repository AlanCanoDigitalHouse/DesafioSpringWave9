package com.springChallenge.SpringChallenge.Services;

import com.springChallenge.SpringChallenge.Dtos.*;

public interface IUserService {
    void follow(int userId, int userIdToFollow);
    FollowersResponse followers(int userId);
    Seller getSellerById(int userId, String user);
    Shopper getSellersByShopper(int userId, String order);
    void unfollow(int userId, int userIdToFollow);
}
