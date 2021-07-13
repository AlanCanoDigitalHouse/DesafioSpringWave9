package com.example.desafiospring.repositories;

import com.example.desafiospring.entities.User;

import java.util.List;

public interface IUserRepository {

    List<User> getAllUsers();

    User findByIdAndType(Long userId, boolean isSeller);

    User findById(Long userId);

}
