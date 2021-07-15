package com.example.demo.Repositories;

import com.example.demo.Exceptions.ExceptionHandler;
import com.example.demo.Models.UserModel;
import com.example.demo.Models.UserRelation;

import java.util.List;

public interface IUserRepository {

    List<UserModel> getUsers();
    UserModel getUserById (int userId) throws ExceptionHandler;
    List<UserRelation> getUsersRelations();
    void addFollower(int FollowerUserId, int FollowingUserId) throws ExceptionHandler;
    void removeFollower(int userId, int userIdToUnfollow) throws ExceptionHandler;
    int getFollowersAmount(int userId) throws ExceptionHandler;
    List<UserModel> getListFollowers(int userId) throws ExceptionHandler;
    List<UserModel> getListFollowing(int userId) throws ExceptionHandler;

}

