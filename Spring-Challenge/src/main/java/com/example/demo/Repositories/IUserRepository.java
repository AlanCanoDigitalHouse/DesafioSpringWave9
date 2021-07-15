package com.example.demo.Repositories;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Models.UserModel;
import com.example.demo.Models.UserRelation;

import java.util.List;

public interface IUserRepository {

    List<UserModel> getUsers();
    UserModel getUserById (int userId) throws CustomExceptionHandler;
    List<UserRelation> getUsersRelations();
    void addFollower(int FollowerUserId, int FollowingUserId) throws CustomExceptionHandler;
    void removeFollower(int userId, int userIdToUnfollow) throws CustomExceptionHandler;
    int getFollowersAmount(int userId) throws CustomExceptionHandler;
    List<UserModel> getListFollowers(int userId) throws CustomExceptionHandler;
    List<UserModel> getListFollowing(int userId) throws CustomExceptionHandler;

}

