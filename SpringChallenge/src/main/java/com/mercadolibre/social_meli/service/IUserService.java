package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.response.FollowerCountResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowersResponseDTO;

public interface IUserService {

    void followUser(Integer userId, Integer userIdToFollow);

    void unfollowUser(Integer userId, Integer userIdToUnfollow);

    FollowerCountResponseDTO getFollowerCount(Integer userId);

    FollowersResponseDTO getFollowers(Integer userId);
}
