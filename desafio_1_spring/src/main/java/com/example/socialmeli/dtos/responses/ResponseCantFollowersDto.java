package com.example.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCantFollowersDto {
    private Integer userId;
    private String userName;
    private Integer followersCount;
}
