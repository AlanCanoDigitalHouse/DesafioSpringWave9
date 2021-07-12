package com.example.desafiospring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowersCountDto {
    private int id;
    private String userName;
    private int followers_count;
}
