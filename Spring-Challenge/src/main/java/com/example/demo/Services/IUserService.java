package com.example.demo.Services;

import com.example.demo.DTOs.FollowListDTO;
import com.example.demo.DTOs.FollowersDTO;
import com.example.demo.Exceptions.*;
import com.example.demo.Models.UserModel;

import java.util.List;

public interface IUserService {

    void addFollower(int follower, int following) throws CustomExceptionHandler;
    FollowersDTO getFollowersAmount(int userId) throws CustomExceptionHandler;
    FollowListDTO getListFollowers(int userId, String order) throws CustomExceptionHandler;
    FollowListDTO getListFollowing(int userId, String order) throws CustomExceptionHandler;
    void removeFollower(int userId, int userIdToUnfollow) throws CustomExceptionHandler;
    List<UserModel> getUsers();

}
