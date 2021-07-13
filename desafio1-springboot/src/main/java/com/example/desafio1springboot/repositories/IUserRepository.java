package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.UserDTO;

public interface IUserRepository {
    UserDTO getUserSeller(Integer userId, Integer userIdToFollow);
}
