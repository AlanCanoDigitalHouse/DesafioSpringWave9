package com.example.socialmeli.services;

import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseRequestDto;
import com.example.socialmeli.exceptions.InvalidOrder;
import com.example.socialmeli.exceptions.UserNotFound;

public interface ISocialMeliUserServices {

    ResponseRequestDto follow(Integer userId, Integer userIdToFollow) throws UserNotFound;

    ResponseRequestDto unfollow(Integer userId, Integer userIdToUnfollow) throws UserNotFound;

    ResponseCantFollowersDto getFollowersCount(Integer userId) throws UserNotFound;

    ResponseFollowersDto getFollowersInfo(Integer userId, String order) throws UserNotFound, InvalidOrder;

    ResponseFollowedDto getFollowedInfo(Integer userId, String order) throws UserNotFound, InvalidOrder;
}
