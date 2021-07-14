package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.exceptions.*;

public interface IUserRepository {
    UserSellerDTO getUserSellerById(Integer userId) throws UserSellerNotFoundExceptions;
    void checkIfUserClientExistsInUserSeller(Integer userId, UserSellerDTO userSeller) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserDTO addFollower(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserClientDTO followedListOfClient_(Integer userId) throws UserClientDoesNotExistsException;
    void unfollowUser_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException;
}
