package com.mercadolibre.desafio.persistence;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseListPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.Post;


public interface ProductPersistence {

    void addPostProduct(RequestPostDto requestPostDto) throws UserException;
    void savePost(Post post);
    Post findPostById(Integer postId) throws PostException;

    ResponseListPost getPostsFollowed(Integer userId) throws UserException, PostException;
}
