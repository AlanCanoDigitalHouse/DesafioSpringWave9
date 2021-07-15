package com.example.demo.Services;

import com.example.demo.DTOs.FollowListDTO;
import com.example.demo.DTOs.FollowersDTO;
import com.example.demo.Exceptions.*;
import com.example.demo.Models.UserModel;

import java.util.List;

public interface IUserService {

    void addFollower(int follower, int following) throws ExceptionHandler;
    FollowersDTO getFollowersAmount(int userId) throws ExceptionHandler;
    FollowListDTO getListFollowers(int userId, String order) throws ExceptionHandler;
    FollowListDTO getListFollowing(int userId, String order) throws ExceptionHandler;
    void removeFollower(int userId, int userIdToUnfollow) throws ExceptionHandler;
    List<UserModel> getUsers();

}
