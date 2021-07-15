package com.example.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CountFollowersResponseDto {

    private int userId;
    private String userName;
    private int followers_count;

    public CountFollowersResponseDto(int userId, String userName, int followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }
}
