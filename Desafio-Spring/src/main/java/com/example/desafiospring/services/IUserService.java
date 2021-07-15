package com.example.desafiospring.services;

import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.response.FollowedsListDTO;
import com.example.desafiospring.dtos.response.FollowersCountDTO;
import com.example.desafiospring.dtos.response.FollowersListDTO;
import com.example.desafiospring.exceptions.UserNotFindOrEqualException;
import com.example.desafiospring.exceptions.UserNotFollowToUserException;
import com.example.desafiospring.exceptions.UserToFollowAlreadyExistException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    ResponseEntity<?> newFollow(Integer userId, Integer userToFollow) throws UserNotFindOrEqualException, UserToFollowAlreadyExistException;

    FollowersCountDTO followerCount(Integer userId) throws UserNotFindOrEqualException;

    FollowersListDTO followerList(Integer userId, String mode) throws UserNotFindOrEqualException;

    FollowedsListDTO followedList(Integer userId, String mode) throws UserNotFindOrEqualException;

    ResponseEntity<?> unfollowUser(Integer userId, Integer userToFollow) throws UserNotFindOrEqualException, UserNotFollowToUserException;

    UserInfo getUserFromRepository(Integer userId);

    List<String> getAllUsers();
}
