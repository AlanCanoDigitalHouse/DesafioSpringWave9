package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.UserAlreadyFollowingSellerException;
import com.example.desafio1springboot.exceptions.UserClientDoesNotExistsException;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    void addNewFollowerToSellerUser(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException;
    UserSellerResponseDTO countFollowersForUser_(Integer userId) throws UserSellerNotFoundExceptions;
    UserSellerDTO followersList(Integer userId) throws UserSellerNotFoundExceptions;
    UserClientDTO followedListByClient_(Integer userId) throws UserClientDoesNotExistsException;
}
