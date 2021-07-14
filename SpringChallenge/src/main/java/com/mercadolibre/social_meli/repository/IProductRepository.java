package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.entity.Post;

import java.util.List;

public interface IProductRepository {
    void saveProduct(ProductRequestDTO productData);

    Post getPost(Integer postId);

    List<Post> getAllPosts();

    List<Post> getUserPosts(Integer userId);

    List<Post> getUserPosts(Integer userId, String order);

}
