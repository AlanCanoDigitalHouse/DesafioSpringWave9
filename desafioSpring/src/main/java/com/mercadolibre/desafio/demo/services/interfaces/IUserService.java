package com.mercadolibre.desafio.demo.services.interfaces;

import com.mercadolibre.desafio.demo.dtos.response.FollowedListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.FollowersCountResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.FollowersListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.SuccessfullyResponseDTO;


public interface IUserService {
    // US 001
    SuccessfullyResponseDTO addFollower(Integer buyerId, Integer sellerId);

    // US 002
    FollowersCountResponseDTO countFollowers(Integer sellerId);

    // US 003
    FollowersListResponseDTO listFollowers(Integer sellerId, String order);

    // US 004
    FollowedListResponseDTO listFollowing(Integer buyerId, String order);

    // US 007
    SuccessfullyResponseDTO unfollow(Integer buyerId, Integer sellerId);
}
