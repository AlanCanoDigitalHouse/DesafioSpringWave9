package com.example.prueba.services;

import com.example.prueba.dtos.UserSellerDTO;
import com.example.prueba.dtos.responseDTO.UserResponseDTO;
import com.example.prueba.exceptions.UserSellerNotFoundExceptions;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<String> followSeller(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions;
    UserResponseDTO countFollowersForUser_(Integer userId) throws UserSellerNotFoundExceptions;
    UserSellerDTO followersList(Integer userId) throws UserSellerNotFoundExceptions;
}
