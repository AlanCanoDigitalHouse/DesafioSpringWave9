package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.repository.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void postNewProduct(ProductRequestDTO productData) {
        this.productRepository.saveProduct(productData);
    }
}
