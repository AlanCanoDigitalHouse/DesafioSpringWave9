package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.response.FollowersCountResponseDTO;
import com.mercadolibre.socialmeli.exception.ServiceException;

public interface UserService {

    void addFollower(Integer followerUserId, Integer followedUserId) throws ServiceException;

    FollowersCountResponseDTO countFollowers(Integer followedUserId);
}
