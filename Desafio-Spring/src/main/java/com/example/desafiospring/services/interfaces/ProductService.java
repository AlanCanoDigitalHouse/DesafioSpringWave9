package com.example.desafiospring.services.interfaces;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.requests.UserIDAndOrderRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowedProductListResponseDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoProductCountResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoProductListResponseDTO;

public interface ProductService {
    NewPostResponseDTO addNewPost(NewPostRequestDTO newPostRequestDTO);

    FollowedProductListResponseDTO followedProductList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO);

    NewPostResponseDTO addNewPromoPost(NewPostRequestDTO newPostRequestDTO);

    PromoProductCountResponseDTO countPromoProducts(OnlyUserIDRequestDTO onlyUserIDRequestDTO);

    PromoProductListResponseDTO promoProductList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO);
}
