package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;

import java.util.List;

public interface IProductService {
    void createNewPost(PostDTO post) throws UserSellerNotFoundExceptions;
    List<PostDTO> show();
}
