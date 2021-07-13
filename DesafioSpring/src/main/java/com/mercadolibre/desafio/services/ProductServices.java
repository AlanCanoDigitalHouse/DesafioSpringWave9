package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseListPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;

import java.util.List;


public interface ProductServices {

    void addPostProduct(RequestPostDto requestPostDto) throws Exception;
    ResponseListPost getPostsFollowed(Integer userId) throws PostException, UserException;

}
