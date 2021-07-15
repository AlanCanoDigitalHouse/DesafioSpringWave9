package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.models.User;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FollowersListResponseDTO {

    private Integer userId;
    private String userName;
    private List<UserResponseDTO> followers;

    public FollowersListResponseDTO(Integer userId, String userName, List<User> followers) {
        this.userId = userId;
        this.userName = userName;
        List<UserResponseDTO> userResponseDTOS;
        this.followers = followers
                .stream()
                .map(u -> new UserResponseDTO(u.getUserId(), u.getUserName()))
                .collect(Collectors.toList());
        /*
        for (User follower: followers) {
            simpleUsers.add(new UserResponseDTO(follower.getUserId(), follower.getUserName()));
        }
        if (simpleUsers == null) {
            throw new RuntimeException("Could not find any followers");
        }
        this.followers = simpleUsers;
         */
    }
}
