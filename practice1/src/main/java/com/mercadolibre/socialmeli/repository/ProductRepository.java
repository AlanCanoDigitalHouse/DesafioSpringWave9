package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.request.ProductDTO;
import com.mercadolibre.socialmeli.model.Post;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    ProductDTO save(ProductDTO product);

    Optional<ProductDTO> findProductByProductId(Integer productId);

    Post savePost(Post post);

    Optional<Post> findPostByPostId(Integer postId);

    List<Post> findPostByUserId(Integer userId);

    void delete(ProductDTO product);
}
