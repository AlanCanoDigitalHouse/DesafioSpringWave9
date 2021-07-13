package com.example.prueba.repositories;

import com.example.prueba.dtos.UserDTO;
import com.example.prueba.dtos.UserSellerDTO;

public interface IUserRepository {
    UserDTO getUserSeller(Integer userId, Integer userIdToFollow);
    UserSellerDTO foundSeller(Integer userId);
}