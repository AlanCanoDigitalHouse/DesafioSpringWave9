package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.*;

public interface IUserService {
    void newFollowClient_ForSeller_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    void unfollowSeller(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException;
    UserSellerResponseDTO countFollowersForUserSeller_(Integer userId) throws UserSellerNotFoundExceptions;
    UserSellerDTO followersClientListBySeller_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
    UserClientDTO followedSellersListByClient_(Integer userId) throws UserClientDoesNotExistsException;
}
