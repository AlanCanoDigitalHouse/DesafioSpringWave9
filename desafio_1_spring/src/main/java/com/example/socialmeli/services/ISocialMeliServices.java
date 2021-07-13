package com.example.socialmeli.services;

import com.example.socialmeli.dtos.PostDto;
import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.dtos.responses.ResponsePostsListDto;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.models.User;
import com.example.socialmeli.repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;

public interface ISocialMeliServices {
    void init();

    void follow(HttpServletResponse response, Integer userId, Integer userIdToFollow) throws UserNotFound;

    void unfollow(HttpServletResponse response, Integer userId, Integer userIdToUnfollow) throws UserNotFound;

    ResponseCantFollowersDto getFollowersCount(HttpServletResponse response, Integer userId) throws UserNotFound;

    ResponseFollowersDto getFollowersInfo(HttpServletResponse response, Integer userId, String order) throws UserNotFound;

    ResponseFollowedDto getFollowedInfo(HttpServletResponse response, Integer userId, String order) throws UserNotFound;

    void post(HttpServletResponse response, PostDto postDto) throws UserNotFound;

    ResponsePostsListDto getPostsInfo(HttpServletResponse response, Integer userId, String order) throws UserNotFound;

}
