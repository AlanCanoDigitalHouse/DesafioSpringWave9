package com.mercadolibre.desafio.services.impl;

import com.mercadolibre.desafio.dtos.requests.RequestPostDiscountDto;
import com.mercadolibre.desafio.dtos.requests.RequestPostDto;
import com.mercadolibre.desafio.dtos.responses.ResponseCountPromo;
import com.mercadolibre.desafio.dtos.responses.ResponseListPost;
import com.mercadolibre.desafio.dtos.responses.ResponseListPromoPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.persistence.ProductPersistence;
import com.mercadolibre.desafio.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    ProductPersistence productPersistence;

    @Override
    public void addPostProduct(RequestPostDto requestPostDto) throws Exception {

        productPersistence.addPostProduct(requestPostDto);
    }

    @Override
    public ResponseListPost getPostsFollowed(Integer userId, String order) throws PostException, UserException {
        return productPersistence.getPostsFollowed(userId, order);
    }

    @Override
    public void addPostDiscountProduct(RequestPostDiscountDto requestPostDiscountDto) throws UserException {
        productPersistence.addPostDiscountProduct(requestPostDiscountDto);
    }

    @Override
    public ResponseCountPromo getCountPromo(Integer userId) throws PostException, UserException {
        return productPersistence.getCountPromo(userId);
    }

    @Override
    public ResponseListPromoPost getPromoPosts(Integer userId) throws UserException, PostException {
        return productPersistence.getPromoPosts(userId);
    }
}
