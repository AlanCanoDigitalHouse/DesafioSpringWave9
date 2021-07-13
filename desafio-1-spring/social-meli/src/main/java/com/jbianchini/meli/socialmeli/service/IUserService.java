package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.UserRequest;
import com.jbianchini.meli.socialmeli.dto.response.FollowersResponse;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFounException;
import com.jbianchini.meli.socialmeli.model.User;

public interface IUserService {
    User create(UserRequest userRequest);

    void follow(Integer userId, Integer userIdToFollow) throws ApplicationException;

    FollowersResponse getFollowersCount(int userId) throws UserNotFounException;

    void createAll() throws ApplicationException;
}
