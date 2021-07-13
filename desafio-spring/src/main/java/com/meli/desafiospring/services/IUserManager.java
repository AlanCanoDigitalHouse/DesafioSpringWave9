package com.meli.desafiospring.services;

import com.meli.desafiospring.models.User;
import org.springframework.http.HttpStatus;

public interface IUserManager {

    public HttpStatus follow(Integer userId, Integer userIdToFollow);

    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow);

    User getUser(Integer userId);

    Integer followersCount(Integer userId);
}
