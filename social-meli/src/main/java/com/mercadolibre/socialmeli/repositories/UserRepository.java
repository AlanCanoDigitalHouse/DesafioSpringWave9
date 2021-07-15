package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dto.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUser();

    User findUserById(int user);
}