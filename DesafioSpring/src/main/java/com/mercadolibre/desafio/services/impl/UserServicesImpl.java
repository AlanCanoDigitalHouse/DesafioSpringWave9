package com.mercadolibre.desafio.services.impl;

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
}
