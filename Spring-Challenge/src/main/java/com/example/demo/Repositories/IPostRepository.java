package com.example.demo.Repositories;

import com.example.demo.DTOs.FollowedPostsDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository {

    void createPost(PostModel post) throws CustomExceptionHandler;
    void createProduct(ProductModel product);
    List<PostModel> getUserPostsById(int userId) throws CustomExceptionHandler;
    ProductModel getProductById(int productId) throws CustomExceptionHandler;
    FollowedPostsDTO getFollowedPosts(int userId, String order, int daysBefore) throws CustomExceptionHandler;

}
