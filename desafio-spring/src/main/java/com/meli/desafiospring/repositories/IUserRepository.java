package com.meli.desafiospring.repositories;

import com.meli.desafiospring.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> getUsers();
    Optional<User> findById(Integer userId);
    List<User> initialize();


}
