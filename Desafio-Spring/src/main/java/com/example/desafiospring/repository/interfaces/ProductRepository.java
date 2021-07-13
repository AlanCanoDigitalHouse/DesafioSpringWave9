package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.ProductRequestDTO;
import com.example.desafiospring.DTOS.responses.ProductResponseDTO;

public interface ProductRepository {
    Integer addProduct(ProductRequestDTO detail);

    ProductResponseDTO getProductResponseDTO(Integer productId);
}
