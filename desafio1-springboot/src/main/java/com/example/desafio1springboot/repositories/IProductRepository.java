package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserPostsResposeDTO;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;

import java.util.List;

public interface IProductRepository {
    void addNewPost(PostDTO post) throws PostNotValidDateException;
    UserPostsResposeDTO listsPostsFromUser_(Integer userId);
    List<PostDTO> show();
}
