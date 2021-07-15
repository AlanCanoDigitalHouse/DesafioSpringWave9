package com.desafio.demo.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FollowersCountResponseDto {

    private int userId;
    private String userName;
    private int followers_count;
}
