package com.mercadolibre.desafio.services.impl;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.persistence.UserPersistence;
import com.mercadolibre.desafio.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserPersistence userPersistence;


    @Override
    public void followUser(Integer userID, Integer userToFollow) throws UserException {
        userPersistence.follow(userID, userToFollow);
    }

    @Override
    public ResponseCountFollowers countFollowers(Integer userId) throws UserException {
        return userPersistence.countFollowers(userId);
    }

    @Override
    public ResponseFollowers getFollowers(Integer userId,String order) throws UserException {
        return userPersistence.getFollowers(userId,order);
    }

    @Override
    public ResponseFollowed getFollowed(Integer userId, String order) throws UserException {
        return userPersistence.getFollowed(userId,order);
    }

    @Override
    public void unfollow(Integer userId, Integer userToUnfollow) throws UserException {
        userPersistence.unfollow(userId,userToUnfollow);
    }
}
