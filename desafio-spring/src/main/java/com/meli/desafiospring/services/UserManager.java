package com.meli.desafiospring.services;

import com.meli.desafiospring.DTOs.response.UserResponseDTO;
import com.meli.desafiospring.models.User;
import com.meli.desafiospring.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements IUserManager{

    UserRepository userRepo;

    @Override
    public HttpStatus follow(Integer userId, Integer userIdToFollow) {
        userRepo.follow(userId, userIdToFollow);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow) {
        userRepo.unfollow(userId, userIdToUnfollow);
        return HttpStatus.OK;
    }

    @Override
    public User getUser(Integer userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public Integer followersCount(Integer userId) {
        return userRepo.followers_count(userId);
    }


}
