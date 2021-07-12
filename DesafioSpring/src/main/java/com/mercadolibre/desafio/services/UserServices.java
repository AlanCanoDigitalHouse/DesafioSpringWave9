package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.exception.UserException;

public interface UserServices {

    void followUser(Integer userID, Integer userToFollow) throws UserException;
    ResponseCountFollowers countFollowers(Integer userId) throws UserException;
}
