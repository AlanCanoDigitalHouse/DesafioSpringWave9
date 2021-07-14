package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.response.FollowedPostsResponseDTO;

public interface IProductService {
    void postNewProduct(ProductRequestDTO productData);

    FollowedPostsResponseDTO getFollowedRecentPosts(Integer userId, String order);
}
