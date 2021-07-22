package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FollowersCountResponseDTO {

    private int userId;
    private String username;
    private int followersCount;

    public FollowersCountResponseDTO(User user, int quantity) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.followersCount = quantity;
    }
}
