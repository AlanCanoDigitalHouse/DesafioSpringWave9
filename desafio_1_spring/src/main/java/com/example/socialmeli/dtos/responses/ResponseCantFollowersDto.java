package com.example.socialmeli.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseCantFollowersDto {
    private Integer userId;
    private String userName;
    private Integer followersCount;
}
