package com.example.desafiospring.services;

import com.example.desafiospring.dtos.ProductCreateDto;

public interface IProductService {

    ProductCreateDto getProductByPostId(Long idPost);

    ProductCreateDto createProduct(ProductCreateDto productCreateDto);

}
