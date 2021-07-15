package com.desafio.demo.services;

import com.desafio.demo.dtos.users.FollowedResponseDto;
import com.desafio.demo.dtos.users.FollowersCountResponseDto;
import com.desafio.demo.dtos.users.FollowersResponseDto;

public interface UserService {
    void followUser(Integer userId, Integer userIdToFollow);

    FollowersCountResponseDto countFollowers(int userId);

    FollowersResponseDto getFollowersList(int userId, String o);

    FollowedResponseDto getFollowedList(int userId, String order);

    void unfollow(int userId, int userIdToUnfollow);

    void initialize();
}
