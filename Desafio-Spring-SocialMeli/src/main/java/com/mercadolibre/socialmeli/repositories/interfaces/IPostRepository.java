package com.mercadolibre.socialmeli.repositories.interfaces;

import com.mercadolibre.socialmeli.dtos.posts.requests.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.requests.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PostResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.ProductResponseDTO;

import java.util.List;

public interface IPostRepository {
    PostResponseDTO addNew(PostRequestDTO post, ProductResponseDTO product);

    List<PostResponseDTO> findAll();

    List<PostResponseDTO> postsOf(Integer userId);

    PostResponseDTO addNewPromo(PromoPostRequestDTO promoPost, ProductResponseDTO product);

    List<PostResponseDTO> promoPostsOf(Integer userId);
}
