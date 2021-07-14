package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.BuyerDTO;
import com.mercadolibre.socialmeli.dtos.SellerDTO;
import com.mercadolibre.socialmeli.dtos.resp.FollowedDTO;
import com.mercadolibre.socialmeli.dtos.resp.FollowersDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;

public interface UserService {

    void follow(Integer userId, Integer userIdToFollow) throws SellerNotFoundException, BuyerNotFoundException, SellerAlreadyFollowedException;

    void unfollow(Integer userId, Integer userIdToUnfollow) throws SellerNotFoundException, BuyerNotFoundException, SellerNotFollowedException;

    FollowersDTO getFollowersList(Integer userId, String order) throws SellerNotFoundException;

    FollowedDTO getFollowedList(Integer userId, String order) throws BuyerNotFoundException;

    FollowersDTO getFollowersCount(Integer userId) throws SellerNotFoundException;

    SellerDTO getSellerById(Integer sellerId) throws SellerNotFoundException;

    BuyerDTO getBuyerById(Integer buyerId) throws BuyerNotFoundException;

}
