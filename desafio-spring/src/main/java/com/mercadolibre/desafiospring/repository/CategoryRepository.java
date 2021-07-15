package com.mercadolibre.desafiospring.repository;

import com.mercadolibre.desafiospring.exception.post.*;
import com.mercadolibre.desafiospring.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryRepository {
    private Map<Integer, PostsRepository> postsRepositories;

    public CategoryRepository() {
        this.postsRepositories = new HashMap<>();
    }

    public PostsRepository getPostRepository(Integer category)
            throws InvalidCategoryException {
        if (this.postsRepositories.getOrDefault(category, null) == null) {
            throw new InvalidCategoryException();
        }

        return this.postsRepositories.get(category);
    }

    public void addCategory(int idCategory) {
        this.postsRepositories.put(idCategory, new PostsRepository());
    }
}
