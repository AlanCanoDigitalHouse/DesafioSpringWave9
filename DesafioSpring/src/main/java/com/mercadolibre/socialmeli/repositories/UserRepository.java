package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.User;

import java.util.List;

public interface UserRepository {
    User findUserById(Integer userId) throws ExceptionUserNotFound;

    List<User> getAllUser();

    void save(User u);
}
