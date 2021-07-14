package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;

public interface IProductRepository {
    void saveProduct(ProductRequestDTO productData);
}
