package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFollowersDto {

    private Long userId;
    private String userName;
    private Long followers_count;

}
