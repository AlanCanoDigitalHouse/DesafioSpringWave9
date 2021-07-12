package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.entities.UserEntity;

import java.awt.print.PrinterJob;

public interface UserRepository {
    void validateExistOrException(Integer id);

    UserEntity getUserByID(Integer userId);
}
