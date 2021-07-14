package com.example.desafiospring.services;

import com.example.desafiospring.dto.response.SellerResponseDTO;
import com.example.desafiospring.dto.response.UserResponseDTO;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;

public interface IUserService {
    void followSeller(Long userId, Long sellerId) throws SellerException;
    
    SellerResponseDTO getNumberOfFollowers(Long sellerId) throws SellerException;

    SellerResponseDTO getFollowers(Long sellerId, String order) throws SellerException;

    UserResponseDTO getFollowed(Long userId, String order) throws UserException;

    void unfollowSeller(Long userId, Long sellerId) throws SellerException;
}
