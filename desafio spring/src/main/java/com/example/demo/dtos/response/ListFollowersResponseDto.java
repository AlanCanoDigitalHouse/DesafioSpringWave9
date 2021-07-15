package com.example.demo.dtos.response;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import lombok.Data;

import java.util.List;

@Data
public class ListFollowersResponseDto {

    private int userId;
    private String userName;
    private List<UserDto> followers;

    public ListFollowersResponseDto(int userId, String userName, List<UserDto> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
    }

}
