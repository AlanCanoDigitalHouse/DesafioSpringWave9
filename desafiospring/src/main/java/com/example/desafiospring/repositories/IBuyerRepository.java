package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.BuyerDTO;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;

public interface IBuyerRepository {

    BuyerDTO findUserById(Integer id) throws UserNotFoundByIdException;


}
