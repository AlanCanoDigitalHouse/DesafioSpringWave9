package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.request.NewPostRequest;
import com.mercadolibre.socialmeli.entity.Product;

public interface IProductService {
    public Product createProduct(NewPostRequest post);
    public Product listProductsFollowed(Integer Id);
}
