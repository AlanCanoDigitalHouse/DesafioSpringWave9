package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.request.PromoProductRequestDTO;
import com.mercadolibre.social_meli.dto.response.FollowedPostsResponseDTO;
import com.mercadolibre.social_meli.dto.response.PromoCountResponseDTO;

public interface IProductService {
    void postNewProduct(ProductRequestDTO productData);

    void postNewPromoProduct(PromoProductRequestDTO productData);

    FollowedPostsResponseDTO getFollowedRecentPosts(Integer userId, String order);

    PromoCountResponseDTO getUserPromoCount(Integer userId);
}
