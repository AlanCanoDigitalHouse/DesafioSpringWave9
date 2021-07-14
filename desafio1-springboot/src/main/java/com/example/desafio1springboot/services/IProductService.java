package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserPostsResposeDTO;
import com.example.desafio1springboot.exceptions.OrderNotValidException;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;

import java.util.List;

public interface IProductService {
    void createNewPost(PostDTO post) throws UserSellerNotFoundExceptions, PostNotValidDateException;
    UserPostsResposeDTO listsPostsFromUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
    List<PostDTO> show();
}
