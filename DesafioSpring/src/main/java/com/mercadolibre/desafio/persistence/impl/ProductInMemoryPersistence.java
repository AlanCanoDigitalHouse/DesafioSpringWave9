package com.mercadolibre.desafio.persistence.impl;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.Post;
import com.mercadolibre.desafio.model.User;
import com.mercadolibre.desafio.persistence.ProductPersistence;
import com.mercadolibre.desafio.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductInMemoryPersistence implements ProductPersistence {

    Map<Integer, Post> database = new HashMap<>();

    @Autowired
    UserPersistence userPersistence;

    @Override
    public void addPostProduct(RequestPostDto requestPostDto) throws UserException {
        User user = userPersistence.getUserById(requestPostDto.getUserId());
        List<Integer> idPosts = user.getPosts();
        Integer id = database.values().size();
        Post post = new Post(id, requestPostDto.getDate()
                , requestPostDto.getDetail(), requestPostDto.getCategory()
                , requestPostDto.getPrice());

        idPosts.add(id);
        savePost(post);
    }

    @Override
    public void savePost(Post post) {
        database.put(post.getId_post(), post);
    }
}
