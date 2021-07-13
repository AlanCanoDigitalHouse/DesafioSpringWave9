package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.exception.EntityAlreadyExist;

public interface UserService {

    void addFollower(Integer followerUserId, Integer followedUserId) throws EntityAlreadyExist;
}
