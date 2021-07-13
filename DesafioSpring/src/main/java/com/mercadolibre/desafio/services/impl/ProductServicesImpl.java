package com.mercadolibre.desafio.services.impl;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseListPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.persistence.ProductPersistence;
import com.mercadolibre.desafio.services.ProductServices;
import com.mercadolibre.desafio.utils.DateUtils;
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
    public ResponseListPost getPostsFollowed(Integer userId) throws PostException, UserException {
        return productPersistence.getPostsFollowed(userId);
    }
}
