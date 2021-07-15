package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.request.PromoProductRequestDTO;
import com.mercadolibre.social_meli.entity.Post;

import java.util.List;

public interface IProductRepository {
    void saveProduct(ProductRequestDTO productData);

    void saveProduct(PromoProductRequestDTO productData);

    Post getPost(Integer postId);

    List<Post> getAllPosts();

    List<Post> getUserPosts(Integer userId, String order);

    List<Post> getMultipleUsersPost(List<Integer> userIds, String order);

    List<Post> getUserPromoPosts(Integer userId);
}
