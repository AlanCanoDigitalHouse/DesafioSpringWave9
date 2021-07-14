package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.PostDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements IProductRepository{
    List<PostDTO> postDatabase = new ArrayList<>();

    @Override
    public void addNewPost(PostDTO post) {
        postDatabase.add(post);
    }

    @Override
    public List<PostDTO> show() {
        return postDatabase;
    }
}
