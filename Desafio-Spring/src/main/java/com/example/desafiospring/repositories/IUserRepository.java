package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.general.UserInfo;

import java.util.Set;


public interface IUserRepository {

    void updateUsersFile();

    UserInfo getUser(Integer userId);

    Set<Integer> getKey();
}
