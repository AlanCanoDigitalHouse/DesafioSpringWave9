package com.meli.desafiospring.services;

import org.springframework.http.HttpStatus;

public interface IUserManager {

    public HttpStatus follow(Integer userId, Integer userIdToFollow);

    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow);
}
