package com.example.socialmeli.dtos.response;

import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowersListResponseDto{

    private Integer userID;
    private String userName;
    private List<UserDto> followers;
}
