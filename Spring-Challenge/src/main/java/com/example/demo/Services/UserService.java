package com.example.demo.Services;

import com.example.demo.DTOs.FollowListDTO;
import com.example.demo.DTOs.FollowersDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Handlers.SortAlphabetically;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.IUserRepository;
import com.example.demo.Services.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<UserModel> getUsers() {
        return userRepository.getUsers();
    }

    //

    @Override
    public void addFollower(int follower, int following) throws CustomExceptionHandler {
        userRepository.addFollower(follower, following);
    }

    @Override
    public void removeFollower(int userId, int unfollowing) throws CustomExceptionHandler {
        userRepository.removeFollower(userId,unfollowing);
    }

    //

    @Override
    public FollowersDTO getFollowersAmount(int userId) throws CustomExceptionHandler {
        String userName = userRepository.getUserById(userId).getUserName();
        int followers = userRepository.getFollowersAmount(userId);
        return new FollowersDTO(userId, userName, followers);
    }

    @Override
    public FollowListDTO getListFollowers(int userId, String order) throws CustomExceptionHandler {
        String userName = userRepository.getUserById(userId).getUserName();
        List<UserModel> followers = userRepository.getListFollowers(userId);
        SortAlphabetically.SortAlphabetically(followers, order);
        FollowListDTO response = new FollowListDTO();
        response.setUserId(userId);
        response.setUserName(userName);
        response.setUsers(UserMapper.toDTO(followers));
        return response;
    }

    @Override
    public FollowListDTO getListFollowing(int userId, String order) throws CustomExceptionHandler {
        String userName = userRepository.getUserById(userId).getUserName();
        List<UserModel> following = userRepository.getListFollowing(userId);
        SortAlphabetically.SortAlphabetically(following, order);
        FollowListDTO response = new FollowListDTO();
        response.setUserId(userId);
        response.setUserName(userName);
        response.setUsers(UserMapper.toDTO(following));
        return response;
    }

}
