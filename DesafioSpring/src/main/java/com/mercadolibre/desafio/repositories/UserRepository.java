package com.mercadolibre.desafio.repositories;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.User;

public interface UserRepository {
    void follow(Integer userID, Integer userToFollowId) throws UserException;
    User getUserById(Integer userId) throws UserException;
    ResponseCountFollowers countFollowers(Integer userId) throws UserException;
}
