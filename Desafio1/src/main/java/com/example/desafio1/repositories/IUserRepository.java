package com.example.desafio1.repositories;

import com.example.desafio1.dto.User;

import java.util.List;

public interface IUserRepository {
    public void addNewFollower(Integer userId, Integer userIdToFollow);
    public void unfollow(Integer userId, Integer userIdToUnfollow);
    public User findUserById(Integer userId);
    public List<User> listFollowers(Integer userId);
    public List<User> listFollowed(Integer userId);
}
