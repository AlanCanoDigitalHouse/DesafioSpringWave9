package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;

public interface IProductService {
    void postNewProduct(ProductRequestDTO productData);
}
