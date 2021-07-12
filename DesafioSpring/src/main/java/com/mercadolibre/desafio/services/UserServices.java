package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;

import java.net.Inet4Address;

public interface UserServices {

    void followUser(Integer userID, Integer userToFollow) throws UserException;
    ResponseCountFollowers countFollowers(Integer userId) throws UserException;
    ResponseFollowers getFollowers(Integer userId) throws UserException;
    ResponseFollowed getFollowed(Integer userId) throws UserException;
}
