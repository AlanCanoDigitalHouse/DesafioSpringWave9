package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.exceptions.UserException;

import java.util.List;

public interface IUser {

    void addFollower(int userId, int userIdToFollow) throws UserException;

    SellerFollowersCountDTO getFollowersCount(int userId) throws UserException;
}
