package com.example.demo.dtos.response;

import com.example.demo.dtos.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class ListFollowedResponseDto {

    private int userId;
    private String userName;
    private List<UserDto> followed;

    public ListFollowedResponseDto(int userId, String userName, List<UserDto> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
    }

}
