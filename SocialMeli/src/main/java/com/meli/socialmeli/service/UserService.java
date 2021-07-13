package com.meli.socialmeli.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public HttpStatus followUser(String userId, String userIdFollow){
        return HttpStatus.OK;
    }
}
