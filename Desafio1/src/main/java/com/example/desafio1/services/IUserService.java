package com.example.desafio1.services;

import com.example.desafio1.dto.User;
import com.example.desafio1.exceptions.annotations.UserId;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface IUserService {
    void follow( Integer userId,  Integer userIdToFollow);
    void unfollow( Integer userId,  Integer userIdToUnfollow);
    Integer calculateNumberOfFollowers(Integer userId);
    List<User> findFollowers(Integer userId, String order);
    List<User> findFollowed(Integer userId, String order);
    User findUserById(Integer userId);
}
