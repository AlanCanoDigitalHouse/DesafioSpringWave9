package com.api.firstspringchallenge.services.user;

import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;
import org.springframework.http.ResponseEntity;

public interface UserServiceI {

    User findSellerById(int userId);

    ResponseEntity<Void> follow(int userId, int otherUserId);

    ResponseEntity getFollowersQuantity(int userid);

    ResponseEntity getFollowers(int userId, String order);

    ResponseEntity getFollowed(int userId);

    ResponseEntity<Void> unfollow(int userId, int otherUserId);

    ResponseEntity getUsers();

}
