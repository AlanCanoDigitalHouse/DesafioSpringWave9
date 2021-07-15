package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.dto.response.FollowedResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersResponseDTO;
import com.mercadolibre.socialmeli.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<UserDTO> allUsers();

    void addFollower(Integer followerUserId, Integer followedUserId) throws ServiceException;

    void unFollow(Integer followerUserId, Integer followedUserId) throws ServiceException;

    FollowersCountResponseDTO countFollowers(Integer followedUserId) throws ServiceException;

    FollowersResponseDTO getFollowers(Integer followedUserId) throws ServiceException;

    FollowersResponseDTO getFollowers(Integer followedUserId, String order) throws ServiceException;

    FollowedResponseDTO getFollowed(Integer followerUserId) throws ServiceException;

    FollowedResponseDTO getFollowed(Integer followerUserId, String order) throws ServiceException;

    UserDTO findUserById(Integer userId);
}
