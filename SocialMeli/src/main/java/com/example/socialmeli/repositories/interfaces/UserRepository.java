package com.example.socialmeli.repositories.interfaces;

import com.example.socialmeli.domains.User;
import com.example.socialmeli.exceptions.DataNotFound;

import java.util.List;

public interface UserRepository {

    User saveUser(User user);

    List<User> findAll();

    User findByUserId(Integer userId) throws DataNotFound;

    User findByUserName(String userName) throws DataNotFound;

}
