package com.mercadolibre.desafio.demo.services.interfaces;

import com.mercadolibre.desafio.demo.dtos.request.NewPostDTO;
import com.mercadolibre.desafio.demo.dtos.request.NewPostPromDTO;
import com.mercadolibre.desafio.demo.dtos.response.SuccessfullyResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicCountResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicPromoListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicsFollowedDTO;

public interface IProductService {
    SuccessfullyResponseDTO createPost(NewPostDTO newPostDTO);

    PublicsFollowedDTO listPublicsFollowed(Integer sellerId, String order);

    SuccessfullyResponseDTO createPromoPost(NewPostPromDTO newPostPromDTO);

    PublicCountResponseDTO countPromoPublic(Integer userId);

    PublicPromoListResponseDTO listPromoPublics(Integer userId);
}
