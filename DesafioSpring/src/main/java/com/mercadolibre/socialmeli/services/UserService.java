package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.User.UserCountDTO;
import com.mercadolibre.socialmeli.dtos.User.UserFollowedDTO;
import com.mercadolibre.socialmeli.dtos.User.UserFollowersDTO;
import com.mercadolibre.socialmeli.dtos.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.ExceptionFollower;
import com.mercadolibre.socialmeli.exceptions.ExceptionOrder;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.User;

import java.util.List;

public interface UserService {
    UserResponseDTO follow(Integer userId, Integer userIdToFollow) throws ExceptionUserNotFound;

    List<User> getAll();

    UserCountDTO countFollowers(Integer userId) throws ExceptionUserNotFound;

    UserFollowersDTO getFollowers(Integer userId, String order) throws ExceptionUserNotFound, ExceptionOrder;

    UserFollowedDTO getFollowed(Integer userId, String order) throws ExceptionUserNotFound, ExceptionOrder;

    UserResponseDTO unfollow(Integer userId, Integer userIdToUnfollow) throws ExceptionUserNotFound, ExceptionFollower;

    User findById(Integer userId) throws ExceptionUserNotFound;
}
