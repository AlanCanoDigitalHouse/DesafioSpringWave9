package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.request.PostDTO;
import com.mercadolibre.socialmeli.dto.request.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostPromoCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostPromoListResponse;
import com.mercadolibre.socialmeli.dto.response.PostResponseListDTO;

public interface ProductService {
    PostDTO createPost(PostDTO post);

    void createPromoPost(PromoPostRequestDTO post);

    PostResponseListDTO findPostFromFollowedUsers(Integer followerId);

    PostResponseListDTO findPostFromFollowedUsers(Integer followerId, String order);

    PostPromoCountResponseDTO countPromoPostsByUser(Integer userId);

    PostPromoListResponse getPromoPostListByUser(Integer userId);
}
