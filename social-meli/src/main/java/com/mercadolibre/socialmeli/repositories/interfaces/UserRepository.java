package com.mercadolibre.socialmeli.repositories.interfaces;

import com.mercadolibre.socialmeli.dtos.BuyerDTO;
import com.mercadolibre.socialmeli.dtos.SellerDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;

public interface UserRepository {

    SellerDTO getSellerById(Integer id) throws SellerNotFoundException;

    void removeFollower(Integer sellerId, Integer followerId) throws SellerNotFoundException, BuyerNotFoundException, SellerNotFollowedException;

    void addFollower(Integer sellerId, Integer followerId) throws SellerNotFoundException, BuyerNotFoundException, SellerAlreadyFollowedException;

    BuyerDTO getBuyerById(Integer id) throws BuyerNotFoundException;

}
