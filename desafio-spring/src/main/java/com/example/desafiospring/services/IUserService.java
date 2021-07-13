package com.example.desafiospring.services;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.exceptions.UserNotExistException;

import java.util.List;
import java.util.Objects;

public interface IUserService {

    UserDto findByUserIdAndType(Long userId, boolean isSeller);

    UserDto findByUserId(Long userId);

    List<UserDto> getAllUsers();

    UserDto validateSellerExist(Long userId) throws UserNotExistException;

    UserDto validateUserExist(Long userId) throws UserNotExistException;

}
