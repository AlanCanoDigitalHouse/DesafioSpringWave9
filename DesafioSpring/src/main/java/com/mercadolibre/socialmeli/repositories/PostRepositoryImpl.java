package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dtos.request.PostRequestDTO;
import com.mercadolibre.socialmeli.models.Post;
import com.mercadolibre.socialmeli.models.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Integer, Post> database = new HashMap<>();


    @Override
    public void addPost(PostRequestDTO postRequestDTO) {
        Post post = new Post();
        Product product = new Product();

        product.setProductName(postRequestDTO.getDetail().getProductName());
        product.setType(postRequestDTO.getDetail().getType());
        product.setBrand(postRequestDTO.getDetail().getBrand());
        product.setColor(postRequestDTO.getDetail().getColor());
        product.setNotes(postRequestDTO.getDetail().getNotes());

        post.setCategory(postRequestDTO.getCategory());
        post.setDate(postRequestDTO.getDate());
        post.setDetail(product);
        post.setPrice(postRequestDTO.getPrice());
        post.setUserId(postRequestDTO.getUserId());

        post.setId_post(database.values().size());

        database.put(post.getId_post(), post);
    }

    @Override
    public Optional<Post> findPostByPostId(Integer postId) {
        Post post = database.get(postId);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) {
        List<Post> posts = database.values().stream().filter(post -> post.getUserId().equals(userId)).collect(Collectors.toList());
        return posts;

    }


    @Override
    public void removePost(Post post) {
        database.remove(post.getId_post());
    }


}

