package com.desafio.demo.repositories;

import com.desafio.demo.dtos.products.request.ProductRequestDto;
import com.desafio.demo.dtos.products.response.PostResponseDto;

import java.util.List;

public interface ProductRepository  {
    void savePost(ProductRequestDto productRequestDto);

    List<PostResponseDto> getAllPostBySellerId(Integer aFollowedId);

    void initialize();



}
