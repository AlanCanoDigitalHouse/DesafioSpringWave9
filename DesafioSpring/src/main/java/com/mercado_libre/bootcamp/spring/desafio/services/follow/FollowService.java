package com.mercado_libre.bootcamp.spring.desafio.services.follow;

import org.springframework.http.HttpStatus;

public interface FollowService {


    public HttpStatus followSeller(int userId, int userIdToFollow);

    public HttpStatus unfollowSeller(int userId, int userIdToFollow);
}
