package com.example.desafiospring.services;

import com.example.desafiospring.dto.response.SellerResponseDTO;
import com.example.desafiospring.dto.response.UserResponseDTO;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;

public interface IUserService {
    void followSeller(Integer userId, Integer sellerId) throws SellerException, UserException;

    SellerResponseDTO getNumberOfFollowers(Integer sellerId) throws SellerException;

    SellerResponseDTO getFollowers(Integer sellerId, String order) throws SellerException;

    UserResponseDTO getFollowed(Integer userId, String order) throws UserException;

    void unfollowSeller(Integer userId, Integer sellerId) throws SellerException;
}
