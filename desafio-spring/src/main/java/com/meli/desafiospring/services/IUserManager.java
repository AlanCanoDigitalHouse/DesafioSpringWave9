package com.meli.desafiospring.services;

import com.meli.desafiospring.DTOs.response.FollowersListResponseDTO;
import com.meli.desafiospring.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserManager {

    public HttpStatus follow(Integer userId, Integer userIdToFollow);

    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow);

    User getUser(Integer userId);

    Integer followersCount(Integer userId);

    FollowersListResponseDTO followersList(Integer sellerId, String order);
    FollowersListResponseDTO followedList(Integer sellerId, String order);

    ResponseEntity<List<User>> initialize();
}
