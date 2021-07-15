package com.example.desafiospring.services;

import com.example.desafiospring.dtos.BuyerFollowerDTO;
import com.example.desafiospring.dtos.SellerDTO;
import com.example.desafiospring.dtos.responsedtos.SellerTotalFollowersResponseDTO;
import com.example.desafiospring.dtos.responsedtos.SellerWithAllFollowersResponseDTO;
import com.example.desafiospring.exceptions.OrderNotExistsException;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;

public interface ISellerService {

    Boolean existsSeller(Integer idSeller) throws UserNotFoundByIdException;

    SellerTotalFollowersResponseDTO getTotalFollowers(Integer idSeller) throws UserNotFoundByIdException;

    SellerDTO findSellerDTOById(Integer idSeller) throws UserNotFoundByIdException;

    void addFollower(SellerDTO sellerDTO, BuyerFollowerDTO follower);

    void removeFollower(SellerDTO sellerDTO, Integer followerId);

    SellerWithAllFollowersResponseDTO getFollowers(Integer idSeller, String sort) throws UserNotFoundByIdException, OrderNotExistsException;
}
