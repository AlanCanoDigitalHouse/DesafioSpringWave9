package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.UserRequestDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowersListDTO;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFoundException;
import com.jbianchini.meli.socialmeli.model.User;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {
    User create(UserRequestDTO userRequestDTO);

    void follow(Integer userId, Integer userIdToFollow, HttpServletResponse response) throws ApplicationException;

    FollowersCountDTO getFollowersCount(int userId) throws UserNotFoundException;

    void createAll(HttpServletResponse response) throws ApplicationException;

    FollowersListDTO getFollowers(int userID) throws UserNotFoundException;

    FollowedListDTO getFollowed(int userID) throws UserNotFoundException;
}
