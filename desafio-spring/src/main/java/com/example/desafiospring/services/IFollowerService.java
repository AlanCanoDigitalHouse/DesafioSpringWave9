package com.example.desafiospring.services;

import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.exceptions.UserNotFollowedException;

public interface IFollowerService {

    void followUserById(Long userId, Long userIdToFollow) throws AlreadyFollowedException, UserNotExistException, SameUserException;

    void unfollowUserById(Long userId, Long userIdToUnfollow) throws UserNotExistException, SameUserException, UserNotFollowedException;

    UserFollowersDto numFollowersByUserId(Long userId) throws UserNotExistException;

    UserFollowersDto getUserFollowers(Long userId, String order) throws UserNotExistException;

    UserFollowersDto getUserFollowed(Long userId, String order) throws UserNotExistException;

}
