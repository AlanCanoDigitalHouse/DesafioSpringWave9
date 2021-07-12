package com.mercadolibre.desafio.services.impl;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.repositories.UserRepository;
import com.mercadolibre.desafio.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;


    @Override
    public void followUser(Integer userID, Integer userToFollow) throws UserException{
        userRepository.follow(userID,userToFollow);
    }

    @Override
    public ResponseCountFollowers countFollowers(Integer userId) throws UserException {
        return userRepository.countFollowers(userId);
    }

    @Override
    public ResponseFollowers getFollowers(Integer userId) throws UserException {
        return userRepository.getFollowers(userId);
    }

    @Override
    public ResponseFollowed getFollowed(Integer userId) throws UserException {
        return userRepository.getFollowed(userId);
    }
}
