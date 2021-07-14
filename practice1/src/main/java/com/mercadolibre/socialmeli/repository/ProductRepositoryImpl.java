package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.dto.ProductDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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
                LocalDate.of(2021, Month.JULY, 2), new ProductDTO(), 0, 150.0));
        posts.put(1, new PostDTO(1, 1,
                LocalDate.of(2021, Month.JULY, 12), new ProductDTO(), 5, 50.0));
        posts.put(2, new PostDTO(2, 1,
                LocalDate.of(2021, Month.JULY, 5), new ProductDTO(), 5, 30.0));
        posts.put(3, new PostDTO(3, 1,
                LocalDate.of(2021, Month.JULY, 5), new ProductDTO(), 8, 380.0));
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
    public Optional<PostDTO> findPostByPostId(Integer postId) {
        return Optional.empty();
    }

    @Override
    public List<PostDTO> findPostByUserId(Integer userId) {
        return posts.values().stream()
                .filter(postDTO ->
                        postDTO.getUserId().equals(userId)
                                && ChronoUnit.WEEKS.between(postDTO.getDate(), LocalDate.now()) < 2)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void delete(ProductDTO product) {

    }
}
