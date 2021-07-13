package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.UserDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;

public interface IUserRepository {
    UserDTO getUserSeller(Integer userId, Integer userIdToFollow);
    UserSellerDTO foundSeller(Integer userId);
}
