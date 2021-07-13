package com.mercadolibre.desafio.persistence;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.User;

public interface UserPersistence {
    void follow(Integer userID, Integer userToFollowId) throws UserException;

    User getUserById(Integer userId) throws UserException;

    ResponseCountFollowers countFollowers(Integer userId) throws UserException;

    ResponseFollowers getFollowers(Integer userId) throws UserException;

    ResponseFollowed getFollowed(Integer userId) throws UserException;


    void unfollow(Integer userID, Integer userToUnfollow);
}
