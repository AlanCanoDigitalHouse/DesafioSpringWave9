package com.example.desafio1.services;

import com.example.desafio1.dto.User;

import java.util.List;

public interface IUserService {
    public void follow(Integer userId, Integer userIdToFollow);
    public Integer calculateNumberOfFollowers(Integer userId);
    public Integer calculateNumberOfFollows(Integer userId);
    public List<User> findFollowers(Integer userId);
    public List<User> findFollows(Integer userId);
}
