package com.mercadolibre.desafio1.repositories.interfaces;

import com.mercadolibre.desafio1.dto.response.ProductResponseDTO;

public interface ProductRepository {
    ProductResponseDTO getProductById(Integer productId);
    ProductResponseDTO addProduct(String productName, String type, String brand, String color, String notes);
}
