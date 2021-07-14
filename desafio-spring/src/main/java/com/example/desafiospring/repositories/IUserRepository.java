package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.models.User;

public interface IUserRepository extends IRepository<User, Long, UserException> {
    Boolean checkIfUserExistsById(Long userId) throws UserException;
}
