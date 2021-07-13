package com.mercadolibre.desafio.persistence;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.Post;


public interface ProductPersistence {

    void addPostProduct(RequestPostDto requestPostDto) throws UserException;
    void savePost(Post post);
}
