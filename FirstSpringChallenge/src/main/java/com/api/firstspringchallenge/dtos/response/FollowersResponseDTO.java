package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
public class FollowersResponseDTO {
    private int userId;
    private String username;
    private List<UserResponseDTO> followers;

    public FollowersResponseDTO(User user, List<User> followers) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.followers = followers.stream().map(f -> new UserResponseDTO(f.getUserId(), f.getUsername())).collect(Collectors.toList());
    }
}
