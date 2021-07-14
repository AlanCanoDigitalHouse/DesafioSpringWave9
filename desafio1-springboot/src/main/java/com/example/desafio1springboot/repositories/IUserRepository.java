package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.exceptions.UserAlreadyFollowingSellerException;
import com.example.desafio1springboot.exceptions.UserClientDoesNotExistsException;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;

public interface IUserRepository {
    UserSellerDTO getUserSellerById(Integer userId) throws UserSellerNotFoundExceptions;
    void checkIfUserClientExistsInUserSeller(Integer userId, UserSellerDTO userSeller) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserDTO addFollower(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserClientDTO followedListOfClient_(Integer userId) throws UserClientDoesNotExistsException;
}
