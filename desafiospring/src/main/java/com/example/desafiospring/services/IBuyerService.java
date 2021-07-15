package com.example.desafiospring.services;

import com.example.desafiospring.dtos.responsedtos.BuyerWithAllFollowsResponseDTO;
import com.example.desafiospring.exceptions.OrderNotExistsException;
import com.example.desafiospring.exceptions.UserAlreadyFollowToSellerException;
import com.example.desafiospring.exceptions.UserNotFollowSellerException;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;

public interface IBuyerService {

    void followSeller(Integer buyer, Integer seller) throws UserNotFoundByIdException, UserAlreadyFollowToSellerException;

    void unFollowSeller(Integer idBuyer, Integer idSeller) throws UserNotFoundByIdException, UserAlreadyFollowToSellerException, UserNotFollowSellerException;

    BuyerWithAllFollowsResponseDTO getFollows(Integer buyerId, String order) throws UserNotFoundByIdException, OrderNotExistsException;

    Boolean existsBuyer(Integer idBuyer);
}
