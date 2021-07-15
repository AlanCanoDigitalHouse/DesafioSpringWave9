package com.mercadolibre.desafio.spring.service;

import com.mercadolibre.desafio.spring.dtos.request.PostDto;
import com.mercadolibre.desafio.spring.dtos.request.UserDto;
import com.mercadolibre.desafio.spring.dtos.response.NumberFollowersResponseDto;
import com.mercadolibre.desafio.spring.dtos.response.PostResponseDto;
import com.mercadolibre.desafio.spring.dtos.response.UserFollowedResponseDto;
import com.mercadolibre.desafio.spring.dtos.response.UserFollowersResponseDto;


public interface IService {

    void followUser(Integer userId, Integer userIdToFollow);

    NumberFollowersResponseDto getNumberOfUsers(Integer userId);

    UserFollowersResponseDto showListFollowers(Integer userId, String sortOrder);

    UserFollowedResponseDto showListFollowed(Integer userId, String sortOrder);

    void createNewPost(PostDto post);

    void unFollowUser(Integer userId, Integer userIdToUnFollow);

    PostResponseDto getPosts(Integer userId, String order);


}
