package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.dto.ProductDTO;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Integer, PostDTO> posts = new HashMap<>();
    private final Map<Integer, ProductDTO> products = new HashMap<>();

    public ProductRepositoryImpl() {
        this.loadDatabase();
    }

    private void loadDatabase() {
        this.loadProducts();
        this.loadPosts();
    }

    private void loadPosts() {
        posts.put(0, new PostDTO(1, 0,
                Date.from(Instant.now()), new ProductDTO(), 0, 150.0));
        posts.put(1, new PostDTO(1, 1,
                Date.from(Instant.now()), new ProductDTO(), 5, 50.0));
    }

    private void loadProducts() {
    }


    @Override
    public ProductDTO save(ProductDTO product) {
        return null;
    }

    @Override
    public Optional<ProductDTO> findProductByProductId(Integer productId) {
        return Optional.empty();
    }

    @Override
    public PostDTO savePost(PostDTO post) {

        if (post.getId_post() == null)
            post.setId_post(posts.values().size());
        posts.put(post.getId_post(), post);
        return post;
    }

    @Override
    public Optional<PostDTO> findPosttByPostId(Integer postId) {
        return Optional.empty();
    }

    @Override
    public void delete(ProductDTO product) {

    }
}
