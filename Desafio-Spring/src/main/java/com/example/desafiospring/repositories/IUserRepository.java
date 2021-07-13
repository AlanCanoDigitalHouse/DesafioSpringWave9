package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.general.User;
import com.example.desafiospring.dtos.general.UserInfo;

import java.util.List;

public interface IUserRepository {

    void updateUsersFile();

    UserInfo getUser(Integer userId);


}
