package com.mercado_libre.bootcamp.spring.desafio.services.post;

import com.mercado_libre.bootcamp.spring.desafio.dtos.request.NewProductRequestDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.request.NewPromoRequestDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.PromoCountResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.PromoListResponseDTO;
import org.springframework.http.HttpStatus;

public interface PostService {

    public HttpStatus addNewProduct(NewProductRequestDTO newProductRequest);

    public HttpStatus addNewPromoProduct(NewPromoRequestDTO newPromoRequest);

    public PromoCountResponseDTO getPromosCount(int sellerId);

    public PromoListResponseDTO getPromoList(int sellerId);
}
