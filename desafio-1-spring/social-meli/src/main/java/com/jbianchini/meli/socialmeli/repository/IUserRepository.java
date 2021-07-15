package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.User;

public interface IUserRepository {
    User save(User user);

    User findByUserId(Integer userId);

    void delete(User user);
}
