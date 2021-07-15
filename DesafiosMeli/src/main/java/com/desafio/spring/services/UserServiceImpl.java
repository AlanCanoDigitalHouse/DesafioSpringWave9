package com.desafio.spring.services;

import com.desafio.spring.dtos.UserDto;
import com.desafio.spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public void addFollower(Integer userId, Integer userIdToFollow) {
        userRepository.addNew(userId, userIdToFollow);
    }

    @Override
    public void deleteFollower(Integer userId, Integer userIdToUnfollow) {
        userRepository.deleteFollower(userId, userIdToUnfollow);
    }

    @Override
    public Integer totalFollowers(Integer userId) {
        return userRepository.allFollowers(userId).size();
    }

    @Override
    public List<UserDto> allFollowers(Integer userId, String order) {
        return userRepository.allFollowers(userId, order);
    }

    @Override
    public List<UserDto> allFollowed(Integer userId, String order) {
        return userRepository.allFollowed(userId, order);
    }
}
