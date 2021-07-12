package com.example.desafiospring.services;

import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserNotExistException;

public interface IFollowerService {

    void followUserById(Long userId, Long userIdToFollow) throws AlreadyFollowedException, UserNotExistException;

    UserFollowersDto numFollowersByUserId(Long userId) throws UserNotExistException;

}
