package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dtos.Product.response.PostUserDTO;
import com.mercadolibre.socialmeli.models.Post;

import java.util.List;

public interface ProductRepository {
    void addPost(Post post);

    int createId();

    List<Post> getAllPost();

    List<PostUserDTO> getLatestPost(Integer userId);
}
