package com.mercado_libre.bootcamp.spring.desafio.services.follow;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowResponseDTO;
import org.springframework.http.HttpStatus;

public interface FollowService {


    public HttpStatus followSeller(int userId, int userIdToFollow);

    public FollowResponseDTO unfollowSeller(int userId, int userIdToFollow);
}
