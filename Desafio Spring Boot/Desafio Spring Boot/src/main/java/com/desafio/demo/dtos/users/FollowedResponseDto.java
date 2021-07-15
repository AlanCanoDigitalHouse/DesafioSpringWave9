package com.desafio.demo.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedResponseDto {

    private int userId;
    private String userName;
    private List<UserResponseDto> followed;
}
