package com.example.socialmeli.repositories;

import com.example.socialmeli.dtos.FollowDto;
import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.responses.AllFollowersResponseDto;
import com.example.socialmeli.dtos.responses.CountFollowersResponseDto;

import java.util.List;

public interface IUserRepository {
    List<UserDto> getUsers();

    UserDto findUser(Integer id);

    void saveUsers();

}
