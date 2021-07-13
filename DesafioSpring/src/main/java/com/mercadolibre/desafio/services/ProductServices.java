package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.exception.UserException;

public interface ProductServices {

    void addPostProduct(RequestPostDto requestPostDto) throws Exception;
}
