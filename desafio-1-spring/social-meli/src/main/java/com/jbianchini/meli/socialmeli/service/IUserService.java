package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersListDTO;
import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFoundException;
import com.jbianchini.meli.socialmeli.model.User;

public interface IUserService {

    ResponseDTO createUser(UserDTO userDTO);

    User findByUserId(Integer userId);

    ResponseDTO follow(Integer userId, Integer userIdToFollow) throws ApplicationException;

    FollowersCountDTO getFollowersCount(Integer userId) throws UserNotFoundException;

    FollowersListDTO getFollowers(Integer userID, String order) throws UserNotFoundException;

    FollowedListDTO getFollowed(Integer userID, String order) throws UserNotFoundException;

    ResponseDTO unFollow(Integer userId, Integer userIdToUnfollow);
}
