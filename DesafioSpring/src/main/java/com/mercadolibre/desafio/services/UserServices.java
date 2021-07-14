package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.responses.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.responses.ResponseFollowed;
import com.mercadolibre.desafio.dtos.responses.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;

public interface UserServices {

    void followUser(Integer userID, Integer userToFollow) throws UserException;

    ResponseCountFollowers countFollowers(Integer userId) throws UserException;

    ResponseFollowers getFollowers(Integer userId, String order) throws UserException;

    ResponseFollowed getFollowed(Integer userId, String order) throws UserException;

    void unfollow(Integer userId, Integer userToUnfollow) throws UserException;


}
