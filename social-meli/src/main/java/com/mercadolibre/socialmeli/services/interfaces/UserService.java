package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.FollowedListDTO;
import com.mercadolibre.socialmeli.dtos.FollowersCountDTO;
import com.mercadolibre.socialmeli.dtos.FollowersListDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;

public interface UserService {

    void follow(Integer userId, Integer userIdToFollow) throws SellerNotFoundException, BuyerNotFoundException, SellerAlreadyFollowedException;

    void unfollow(Integer userId, Integer userIdToUnfollow) throws SellerNotFoundException, BuyerNotFoundException, SellerNotFollowedException;

    FollowersListDTO getFollowersList(Integer userId, String order) throws SellerNotFoundException;

    FollowedListDTO getFollowedList(Integer userId, String order) throws BuyerNotFoundException;

    FollowersCountDTO getFollowersCount(Integer userId) throws SellerNotFoundException;


}
