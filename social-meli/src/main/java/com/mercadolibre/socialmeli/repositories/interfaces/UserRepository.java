package com.mercadolibre.socialmeli.repositories.interfaces;

import com.mercadolibre.socialmeli.dtos.BuyerDTO;
import com.mercadolibre.socialmeli.dtos.SellerDTO;
import com.mercadolibre.socialmeli.exceptions.RecordNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;

public interface UserRepository {

    SellerDTO getSellerById(Integer id) throws RecordNotFoundException;

    void removeFollower(Integer sellerId, Integer followerId) throws RecordNotFoundException;

    void addFollower(Integer sellerId, Integer followerId) throws RecordNotFoundException, SellerAlreadyFollowedException;

    BuyerDTO getBuyerById(Integer id) throws RecordNotFoundException;

}
