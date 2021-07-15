package com.meli.itbootcamp.services;

import com.meli.itbootcamp.dto.ListPostSellerDTO;
import com.meli.itbootcamp.dto.ResponseDTO;
import com.meli.itbootcamp.dto.UserCountDTO;
import com.meli.itbootcamp.dto.UserFollowsDTO;
import com.meli.itbootcamp.exceptions.UserException;
import com.meli.itbootcamp.model.UserNonSeller;
import com.meli.itbootcamp.model.UserSeller;

public interface UserServices {

    ResponseDTO followSeller(Integer nonSeller, Integer Seller) throws UserException;
    UserCountDTO numberFollowers(Integer seller) throws UserException;
    UserFollowsDTO followersList(Integer seller,String orderBy) throws UserException;
    UserFollowsDTO followedList(Integer nonSeller,String orderBy) throws UserException;
    ResponseDTO unfollowSeller(Integer nonSeller,Integer seller) throws UserException;
    UserSeller findUserSellerById(Integer seller);
    UserNonSeller findUserNonSellerById(Integer nonSeller);
//    ListPostSellerDTO getPost(Integer nonSeller);

}