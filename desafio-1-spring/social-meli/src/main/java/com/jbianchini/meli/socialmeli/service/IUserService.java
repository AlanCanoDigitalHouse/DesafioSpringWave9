package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.UserRequest;
import com.jbianchini.meli.socialmeli.dto.response.FollowedListResponse;
import com.jbianchini.meli.socialmeli.dto.response.FollowersCountResponse;
import com.jbianchini.meli.socialmeli.dto.response.FollowersListResponse;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFoundException;
import com.jbianchini.meli.socialmeli.model.User;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {
    User create(UserRequest userRequest);

    void follow(Integer userId, Integer userIdToFollow, HttpServletResponse response) throws ApplicationException;

    FollowersCountResponse getFollowersCount(int userId) throws UserNotFoundException;

    void createAll(HttpServletResponse response) throws ApplicationException;

    FollowersListResponse getFollowers(int userID) throws UserNotFoundException;

    FollowedListResponse getFollowed(int userID) throws UserNotFoundException;
}
