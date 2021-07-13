package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;
import com.desafiospring.socialmeli.exceptions.UserException;


public interface IUser {

    void addFollower(int userId, int userIdToFollow) throws UserException;

    SellerFollowersCountDTO getFollowersCount(int userId) throws UserException;

    SellerFollowersDTO getFollowers(int userId) throws UserException;

    BuyerFollowedDTO getFollowed(int userId) throws UserException;
}
