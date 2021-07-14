package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.PostDTO;

import java.util.List;

public interface IProductRepository {
    void addNewPost(PostDTO post);
    List<PostDTO> show();
}
