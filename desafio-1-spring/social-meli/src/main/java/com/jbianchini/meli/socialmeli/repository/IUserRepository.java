package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.UserDTO;

import java.util.Optional;

public interface IUserRepository {
    UserDTO save(UserDTO user);

    Optional<UserDTO> findByUserId(Integer userId);

    void delete(UserDTO user);
}
