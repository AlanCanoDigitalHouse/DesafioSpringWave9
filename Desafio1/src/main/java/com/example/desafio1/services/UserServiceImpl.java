package com.example.desafio1.services;

import com.example.desafio1.dto.User;
import com.example.desafio1.exceptions.annotations.UserId;
import com.example.desafio1.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

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
    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        userRepository.unfollow(userId, userIdToUnfollow);
    }

    @Override
    public Integer calculateNumberOfFollowers(Integer userId) {
        return userRepository.listFollowers(userId).size();
    }

    @Override
    public List<User> findFollowers(Integer userId, String order) {
        List<User> users = userRepository.listFollowers(userId);
        sortBy(users, order);
        return users;
    }

    @Override
    public List<User> findFollowed(Integer userId, String order) {
        List<User> users = userRepository.listFollowed(userId);
        sortBy(users, order);
        return users;
    }

    private void sortBy(List<User> users, String order){
        if(Objects.isNull(order)) return;
        switch (order.toLowerCase(Locale.ROOT)){
            case "name_asc":
                users.sort((u1, u2) -> u1.getUserName().compareToIgnoreCase(u2.getUserName()));
                break;
            case "name_desc":
                users.sort((u1, u2) -> u2.getUserName().compareToIgnoreCase(u1.getUserName()));
                break;
            default:
                break;
        }
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findUserById(userId);
    }
}
