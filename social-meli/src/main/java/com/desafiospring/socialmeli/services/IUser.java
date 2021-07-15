package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Seller;
import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;
import com.desafiospring.socialmeli.exceptions.UserException;


public interface IUser {

    Seller getSeller(int userId) throws UserException;

    Buyer getBuyer(int userId) throws UserException;

    void addFollower(int userId, int userIdToFollow) throws UserException;

    SellerFollowersCountDTO getFollowersCount(int userId) throws UserException;

    SellerFollowersDTO getFollowers(int userId, String order) throws UserException;

    BuyerFollowedDTO getFollowed(int userId, String order) throws UserException;

    void removeFollower(int userId, int userIdToUnfollow) throws UserException;
}
