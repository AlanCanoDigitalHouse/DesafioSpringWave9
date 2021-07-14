package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.models.Seller;

import java.util.List;

public interface ISellerRepository extends IRepository<Seller, Integer, SellerException> {
    
    Seller addFollower(Integer userId, Integer userIdToFollow) throws SellerException;

    Seller removeFollower(Integer userId, Integer userIdToFollow) throws SellerException;

    List<Seller> findByFollowerUserId(Integer userId);

    Boolean checkIfSellerExistsById(Integer sellerId) throws SellerException;
}
