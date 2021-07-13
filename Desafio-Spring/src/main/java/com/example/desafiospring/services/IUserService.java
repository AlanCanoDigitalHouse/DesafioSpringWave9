package com.example.desafiospring.services;

import com.example.desafiospring.dtos.response.FollowedsListDTO;
import com.example.desafiospring.dtos.response.FollowersCountDTO;
import com.example.desafiospring.dtos.response.FollowersListDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<?> newFollow(Integer userId, Integer userToFollow) throws UserNotFindException;

    FollowersCountDTO followerCount(Integer userId) throws UserNotFindException;

    FollowersListDTO followerList(Integer userId) throws UserNotFindException;

    FollowedsListDTO followedList(Integer userId) throws UserNotFindException;
}
