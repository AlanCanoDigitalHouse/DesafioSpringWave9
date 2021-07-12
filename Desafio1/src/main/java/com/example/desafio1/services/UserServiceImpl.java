package com.example.desafio1.services;

import com.example.desafio1.dto.User;
import com.example.desafio1.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        userRepository.addNewFollower(userId, userIdToFollow);
    }

    @Override
    public Integer calculateNumberOfFollowers(Integer userId) {
        return userRepository.listFollowers(userId).size();
    }

    @Override
    public Integer calculateNumberOfFollows(Integer userId) {
        return userRepository.listFollows(userId).size();
    }

    @Override
    public List<User> findFollowers(Integer userId) {
        return null;
    }

    @Override
    public List<User> findFollows(Integer userId) {
        return null;
    }
}
