package com.example.socialmeli.repositories;

import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.models.User;

import java.util.HashMap;

public interface IUserRepository {

    User find(Integer userId) throws UserNotFound;

    void add(User user);
}
