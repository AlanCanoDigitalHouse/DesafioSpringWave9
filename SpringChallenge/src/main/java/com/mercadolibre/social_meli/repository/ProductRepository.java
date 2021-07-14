package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.response.ProductResponseDTO;
import com.mercadolibre.social_meli.entity.Post;
import com.mercadolibre.social_meli.exception.InvalidQueryParamException;
import com.mercadolibre.social_meli.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    public List<Post> getUserPosts(Integer userId, String order) {
        var posts = getAllPosts();
        var userPosts = posts.stream().filter(post -> post.getUserId().equals(userId))
                .collect(Collectors.toList());

        var postOrder = Objects.isNull(order) ? "date_desc" : order;
        this.sortPostDates(userPosts, postOrder);

        return userPosts;
    }

    @Override
    public List<Post> getMultipleUsersPost(List<Integer> userIds, String order) {
        var posts = getAllPosts();
        var userPosts = posts.stream().filter(post -> userIds.contains(post.getUserId()))
                .collect(Collectors.toList());

        var postOrder = Objects.isNull(order) ? "date_desc" : order;
        this.sortPostDates(userPosts, postOrder);

        return userPosts;
    }

    private void sortPostDates(List<Post> posts, String order) {
        var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        switch (order) {
            case "date_asc":
                posts.sort(Comparator.comparing(p -> LocalDate.parse(p.getDate(), dateFormatter)));
                break;
            case "date_desc":
                posts.sort(Comparator.comparing(p -> LocalDate.parse(p.getDate(), dateFormatter)));
                Collections.reverse(posts);
                break;
            default:
                throw new InvalidQueryParamException("Order param must be date_asc or date_desc");
        }
    }
}
