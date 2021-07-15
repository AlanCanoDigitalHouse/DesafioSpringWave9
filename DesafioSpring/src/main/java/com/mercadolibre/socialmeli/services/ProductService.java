package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.Product.request.PostDTO;
import com.mercadolibre.socialmeli.dtos.Product.request.PromoPostDTO;
import com.mercadolibre.socialmeli.dtos.Product.response.CountPromoDTO;
import com.mercadolibre.socialmeli.dtos.Product.response.UserPostsDTO;
import com.mercadolibre.socialmeli.dtos.Product.response.UserPromoPostDTO;
import com.mercadolibre.socialmeli.dtos.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.ExceptionOrder;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.Post;

import java.util.List;

public interface ProductService {
    UserResponseDTO createPost(PostDTO post) throws ExceptionUserNotFound;

    List<Post> getAll();

    UserPostsDTO getAllPosts(Integer userId, String order) throws ExceptionUserNotFound, ExceptionOrder;

    UserResponseDTO createPromoPost(PromoPostDTO post) throws ExceptionUserNotFound;

    CountPromoDTO countPromoPosts(Integer userId) throws ExceptionUserNotFound;

    UserPromoPostDTO getPromoPost(Integer userId) throws ExceptionUserNotFound;
}
