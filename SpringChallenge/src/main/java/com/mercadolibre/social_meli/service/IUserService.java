package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.response.FollowedResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowerCountResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowersResponseDTO;
import com.mercadolibre.social_meli.dto.response.UserResponseDTO;

public interface IUserService {

    UserResponseDTO getUserById(Integer userId);

    void followUser(Integer userId, Integer userIdToFollow);

    void unfollowUser(Integer userId, Integer userIdToUnfollow);

    FollowerCountResponseDTO getFollowerCount(Integer userId);

    FollowersResponseDTO getFollowers(Integer userId, String order);

    FollowedResponseDTO getFollowed(Integer userId, String order);
}
