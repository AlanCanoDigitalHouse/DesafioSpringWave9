package com.example.socialmeli.dtos.response;

import com.example.socialmeli.dtos.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class FollowedListResponseDto {

    private Integer userId;
    private String userName;
    private List<UserDto> followed;
}
