package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.*;

public interface IUserService {
    void addNewFollowerToSellerUser(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserSellerResponseDTO countFollowersForUser_(Integer userId) throws UserSellerNotFoundExceptions;
    UserSellerDTO followersList(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
    UserClientDTO followedListByClient_(Integer userId) throws UserClientDoesNotExistsException;
    void unfollowSeller_By_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException;
}
