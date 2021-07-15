package com.mercadolibre.desafio.demo.services;

import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NotFoundUserException;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.RepeatUserIdException;
import com.mercadolibre.desafio.demo.models.UserModel;
import com.mercadolibre.desafio.demo.repositories.DataBaseUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {
    DataBaseUserRepository dataBaseUserRepository;

    public ValidationService(DataBaseUserRepository dataBaseUserRepository) {
        this.dataBaseUserRepository = dataBaseUserRepository;
    }



}
