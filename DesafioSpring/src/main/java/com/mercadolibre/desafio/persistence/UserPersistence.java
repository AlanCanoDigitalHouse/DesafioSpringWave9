package com.mercadolibre.desafio.persistence;

import com.mercadolibre.desafio.dtos.responses.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.responses.ResponseFollowed;
import com.mercadolibre.desafio.dtos.responses.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.User;

public interface UserPersistence {
    void follow(Integer userID, Integer userToFollowId) throws UserException;

    User getUserById(Integer userId) throws UserException;

    ResponseCountFollowers countFollowers(Integer userId) throws UserException;

    ResponseFollowers getFollowers(Integer userId, String order) throws UserException;

    ResponseFollowed getFollowed(Integer userId, String order) throws UserException;

    void unfollow(Integer userId, Integer userToUnfollow) throws UserException;
}
