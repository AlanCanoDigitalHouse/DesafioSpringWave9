package com.example.desafiospring.service;

import com.example.desafiospring.dtos.response.PostsUserResponseDTO;
import com.example.desafiospring.dtos.request.UserRequestDTO;
import com.example.desafiospring.dtos.response.UserResponseDTO;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserIdEqualUserToFollowException;
import com.example.desafiospring.exceptions.UserNotExistsException;
import com.example.desafiospring.exceptions.UserNotFollowedException;

import java.util.List;

public interface IUserService {

    List<UserRequestDTO> getUsers();

    void follow(int pUserId, int pUserIdToFollow) throws UserIdEqualUserToFollowException, UserNotExistsException, AlreadyFollowedException, UserNotFollowedException;

    void unfollow(int pUserId, int pUserIdToFollow) throws UserIdEqualUserToFollowException, UserNotExistsException, UserNotFollowedException, AlreadyFollowedException;

    UserResponseDTO getUserWithFollowersQuantity(int pUserIdToFollow) throws UserNotExistsException;

    UserRequestDTO getUserFollowers(int pUserId, String pOrder) throws UserNotExistsException;

    UserRequestDTO getUserFollowed(int pUserId, String pOrder) throws UserNotExistsException;

    PostsUserResponseDTO getPostsByUser(int pUserId, String pOrder) throws UserNotExistsException;


}
