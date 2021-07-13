package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.entities.UserEntity;

import java.util.List;

public interface UserRepository {
    void validateExistOrException(Integer id);

    UserEntity getUserByID(Integer userId);

    List<UserEntity> getUsersByID(List<Integer> followerIDS);
}
