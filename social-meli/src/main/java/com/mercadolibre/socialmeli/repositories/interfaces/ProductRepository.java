package com.mercadolibre.socialmeli.repositories.interfaces;

import com.mercadolibre.socialmeli.dtos.PostDTO;

import java.util.List;

public interface ProductRepository {

    void createPost(PostDTO postDTO);

    List<PostDTO> getPostsBySeller(Integer sellerId);

}
