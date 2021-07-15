package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.posts.requests.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PostListResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.requests.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PostResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PromoPostListResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PromoPostQtyResponseDTO;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;

public interface IPostService {
    PostResponseDTO createPost(PostRequestDTO post) throws UserNotFoundException;

    PostListResponseDTO postsListOf(Integer userId, String order) throws UserNotFoundException;

    PostResponseDTO createPromoPost(PromoPostRequestDTO promoPost) throws UserNotFoundException;

    PromoPostQtyResponseDTO countPromoPostsOf(Integer userId) throws UserNotFoundException;

    PromoPostListResponseDTO promoPostsListOf(Integer userId) throws UserNotFoundException;
}
