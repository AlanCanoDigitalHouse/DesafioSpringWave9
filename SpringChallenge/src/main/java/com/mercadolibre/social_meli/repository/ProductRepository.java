package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends JSONRepository<Post> implements IProductRepository {

    private static final String POSTS_DIR = "classpath:static/posts.json";
    private final IUserRepository userRepository;

    public ProductRepository(IUserRepository userRepository) {
        super(POSTS_DIR, Post.class);
        this.userRepository = userRepository;
    }

    @Override
    public void saveProduct(ProductRequestDTO productData) {
        // If user doesn't exist this method will throw a custom runtime exception
        this.userRepository.getUser(productData.getUserId());

        var posts = getData();
        var newPost = new Post(posts.size(), productData.getUserId(), productData.getDate(), productData.getDetail(), productData.getCategory(), productData.getPrice());

        posts.add(newPost);
        writeDatabase(posts);
    }
}
