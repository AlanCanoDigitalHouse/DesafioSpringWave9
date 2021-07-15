package com.desafio.demo.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowersResponseDto {

    private int userId;
    private String userName;
    private List<UserResponseDto> followers;
}
