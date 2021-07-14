package com.mercadolibre.desafio.services;

import com.mercadolibre.desafio.dtos.requests.RequestPostDiscountDto;
import com.mercadolibre.desafio.dtos.requests.RequestPostDto;
import com.mercadolibre.desafio.dtos.responses.ResponseCountPromo;
import com.mercadolibre.desafio.dtos.responses.ResponseListPost;
import com.mercadolibre.desafio.dtos.responses.ResponseListPromoPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;


public interface ProductServices {

    void addPostProduct(RequestPostDto requestPostDto) throws Exception;

    ResponseListPost getPostsFollowed(Integer userId, String order) throws PostException, UserException;

    void addPostDiscountProduct(RequestPostDiscountDto requestPostDiscountDto) throws UserException;

    ResponseCountPromo getCountPromo(Integer userId) throws PostException, UserException;

    ResponseListPromoPost getPromoPosts(Integer userId) throws UserException, PostException;

}
