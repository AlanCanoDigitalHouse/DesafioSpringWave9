package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.exceptions.*;

public interface IUserRepository {
    UserSellerDTO getUserSellerByIdAndCheckIfExists(Integer userId) throws UserSellerNotFoundExceptions;
    UserDTO addClient_FollowerToUserSeller_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserClientDTO followedListOfClient_(Integer userId) throws UserClientDoesNotExistsException;
    void checkIfUserClientExistsInUserSeller(Integer userId, UserSellerDTO userSeller) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    void unfollowByClient_TheUserSeller_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException;
}
