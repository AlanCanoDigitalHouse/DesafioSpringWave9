package com.example.socialmeli.services.interfaces;

import com.example.socialmeli.domains.User;
import com.example.socialmeli.dtos.response.*;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.exceptions.OrderInvalidFormatException;
import com.example.socialmeli.exceptions.SameUserException;

import java.util.List;

public interface UserServices {

    UserResponseDTO save(String userName);

    List<UserResponseDTO> findAll();

    User findByUserId(Integer userId) throws DataNotFound;

    SuccessResponseDTO followUser(Integer userId, Integer userIdToFollow) throws DataNotFound, SameUserException;

    UserFollowersDTO findFollowersByUser(String userId) throws DataNotFound;

    UserFollowersDetailsDTO findFollowersDetailByUser(String userId) throws DataNotFound;

    UserFollowersDetailsDTO findFollowersDetailByUser(Integer userId, String order) throws DataNotFound, OrderInvalidFormatException;

    UserFollowedDetailsDTO findFollowedDetailByUser(String userId) throws DataNotFound;

    UserFollowedDetailsDTO findFollowedDetailByUser(Integer userId, String order) throws DataNotFound, OrderInvalidFormatException;

    SuccessResponseDTO unFollowUser(Integer userId, Integer userIdToUnFollow) throws DataNotFound, SameUserException;
}
