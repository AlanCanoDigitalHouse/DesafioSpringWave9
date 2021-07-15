package com.example.socialmeli.dtos.response;

import lombok.Data;

@Data
public class FollowersCountResponseDto {

    private Integer userId;
    private String userName;
    private Integer followers_count;
}
