package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.User;

import java.util.Optional;

public interface IUserRepository {
    User save(User user);

    Optional<User> findByUserId(Integer userId);

    void delete(User user);
}
