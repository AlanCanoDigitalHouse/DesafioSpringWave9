package com.mercadolibre.desafio1.services.interfaces;

import com.mercadolibre.desafio1.dto.response.UserFollowersCountDTO;
import com.mercadolibre.desafio1.dto.response.UserFollowListDTO;
import com.mercadolibre.desafio1.exceptions.UserFollowException;
import com.mercadolibre.desafio1.exceptions.UserNotExistException;

public interface UserService {
    void followUser(Integer userId, Integer userIdToFollow) throws UserNotExistException, UserFollowException;
    void unfollowUser(Integer userId, Integer userIdToFollow) throws UserFollowException, UserNotExistException;
    UserFollowersCountDTO countFollowUser(Integer userId) throws UserNotExistException;
    UserFollowListDTO listFollowersUsers(Integer userId, String order) throws UserNotExistException;
    UserFollowListDTO listFollowedUsers(Integer userId, String order) throws UserNotExistException;
}
