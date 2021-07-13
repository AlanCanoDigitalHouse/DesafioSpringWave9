package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.dtos.ResponseListPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;


public interface ProductServices {

    void addPostProduct(RequestPostDto requestPostDto) throws Exception;
    ResponseListPost getPostsFollowed(Integer userId, String order) throws PostException, UserException;

}
