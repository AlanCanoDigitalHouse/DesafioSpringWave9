package com.meli.desafiospring.services;

import com.meli.desafiospring.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    UserRepository userRepo;

    public HttpStatus follow(Integer userId, Integer userIdToFollow) {
        return userRepo.follow(userId, userIdToFollow);
    }
}
