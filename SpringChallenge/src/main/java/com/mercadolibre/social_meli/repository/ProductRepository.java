package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.entity.Post;
import com.mercadolibre.social_meli.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Post getPost(Integer postId) {
        var posts = this.getAllPosts();
        var requestedPost = posts.stream().filter(post -> post.getId_post().equals(postId))
                .findFirst();

        if (requestedPost.isPresent()) {
            return requestedPost.get();
        }

        throw new ResourceNotFoundException("Requested post does not exist");
    }

    @Override
    public List<Post> getAllPosts() {
        return this.getData();
    }

    @Override
    public List<Post> getUserPosts(Integer userId) {
        var posts = getAllPosts();
        var userPosts = posts.stream().filter(post -> post.getUserId().equals(userId))
                .collect(Collectors.toList());

        var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        userPosts.sort(Comparator.comparing(p -> LocalDate.parse(p.getDate(), dateFormatter)));
        Collections.reverse(userPosts);

        return userPosts;
    }

    @Override
    public List<Post> getUserPosts(Integer userId, String order) {
        return null;
    }
}
