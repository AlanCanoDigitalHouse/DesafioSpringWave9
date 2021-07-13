package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.models.Seller;

import java.util.List;

public interface ISellerRepository extends IRepository<Seller, Long, SellerException> {
    Seller addFollower(Long userId, Long userIdToFollow) throws SellerException;

    Seller removeFollower(Long userId, Long userIdToFollow);

    List<Seller> findByUserId(Long userId);
}
