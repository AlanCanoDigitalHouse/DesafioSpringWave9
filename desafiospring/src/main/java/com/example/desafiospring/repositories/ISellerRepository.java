package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.SellerDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;

public interface ISellerRepository {

    SellerDTO findSellerById(Integer sellerId) throws UserNotFoundByIdException;
}
