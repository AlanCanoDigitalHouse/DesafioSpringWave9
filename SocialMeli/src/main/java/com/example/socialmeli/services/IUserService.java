package com.example.socialmeli.services;

import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.responses.AllFollowedResponseDto;
import com.example.socialmeli.dtos.responses.AllFollowersResponseDto;
import com.example.socialmeli.dtos.responses.AllUserResponseDto;
import com.example.socialmeli.dtos.responses.CountFollowersResponseDto;

public interface IUserService {

    void follow(Integer userId, Integer idToFollow);

    CountFollowersResponseDto countFollowers(Integer userId);

    AllFollowersResponseDto allFollowers(Integer userId, String order);

    AllFollowedResponseDto allFollowed(Integer userId, String order);

    void unfollow(Integer userId, Integer idToUnfollow);

    UserDto findUser(Integer userId);

    AllUserResponseDto allUsers();
}
