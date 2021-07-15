package com.desafio.demo.services;

import com.desafio.demo.dtos.products.request.ProductRequestDto;
import com.desafio.demo.dtos.products.response.ProductResponseDto;

public interface ProductService {
    void releasePost(ProductRequestDto productRequestDto);

    ProductResponseDto getLastsPosts(int userId, String order);
}
