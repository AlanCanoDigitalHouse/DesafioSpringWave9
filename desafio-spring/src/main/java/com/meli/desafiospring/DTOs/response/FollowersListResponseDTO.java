package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.models.User;

import java.util.List;

public class FollowersListResponseDTO {

    Integer userId;
    String userName;
    List<UserResponseDTO> followers;

    public FollowersListResponseDTO(Integer userId, String userName, List<User> followers) {
        this.userId = userId;
        this.userName = userName;
        List<UserResponseDTO> simpleUsers = null;
        for (User follower: followers) {
            simpleUsers.add(new UserResponseDTO(follower.getUserId(), follower.getUserName()));
        }
        if (simpleUsers == null)
            throw new RuntimeException("Could not find any followers");
        this.followers = simpleUsers;
    }
}
