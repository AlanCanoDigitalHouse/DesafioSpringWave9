package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    ProductDTO save(ProductDTO product);

    Optional<ProductDTO> findProductByProductId(Integer productId);

    PostDTO savePost(PostDTO post);

    Optional<PostDTO> findPostByPostId(Integer postId);

    List<PostDTO> findPostByUserId(Integer userId);

    void delete(ProductDTO product);
}
