package com.example.desafiospring.services;

import com.example.desafiospring.dtos.UserDto;

import java.util.List;

public interface IUserService {

    UserDto findByUserId(Long userId, boolean isSeller);

    List<UserDto> getAllUsers();

}
