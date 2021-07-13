package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.ProductRequestDTO;

public interface ProductRepository {
    Integer addProduct(ProductRequestDTO detail);
}
