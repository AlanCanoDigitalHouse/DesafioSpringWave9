package com.socialmeli.services;

import com.socialmeli.dtos.response.FollowCountDTO;
import com.socialmeli.dtos.response.FollowedListDTO;
import com.socialmeli.dtos.response.FollowerListDTO;

public interface IUsersService {

    void followUser(Integer idUser, Integer idFollow);

    public FollowCountDTO countFollowers(Integer idUser);

    public FollowerListDTO listFollowers(Integer idUser);

    public FollowedListDTO listFollowed(Integer idUser);
}
