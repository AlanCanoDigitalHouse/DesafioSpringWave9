package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.responseDTO.UserResponseDTO;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<String> followSeller(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions;
    UserResponseDTO countFollowersForUser_(Integer userId) throws UserSellerNotFoundExceptions;
}
