package com.mercadolibre.desafio.persistence;

import com.mercadolibre.desafio.dtos.requests.RequestPostDiscountDto;
import com.mercadolibre.desafio.dtos.requests.RequestPostDto;
import com.mercadolibre.desafio.dtos.responses.ResponseCountPromo;
import com.mercadolibre.desafio.dtos.responses.ResponseListPost;
import com.mercadolibre.desafio.dtos.responses.ResponseListPromoPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.Post;


public interface ProductPersistence {

    void addPostProduct(RequestPostDto requestPostDto) throws UserException;

    void savePost(Post post);

    Post findPostById(Integer postId) throws PostException;

    ResponseListPost getPostsFollowed(Integer userId, String order) throws UserException, PostException;

    void addPostDiscountProduct(RequestPostDiscountDto requestPostDiscountDto) throws UserException;

    ResponseCountPromo getCountPromo(Integer userId) throws UserException, PostException;

    ResponseListPromoPost getPromoPosts(Integer userId) throws UserException, PostException;

}
