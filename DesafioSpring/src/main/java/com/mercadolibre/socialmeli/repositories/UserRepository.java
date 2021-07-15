package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.models.User;

import java.util.Optional;

public interface UserRepository {
    User addUser(User user);

    Optional<User> findUserByUserId(Integer userId);

    void removeUser(User user);

}
