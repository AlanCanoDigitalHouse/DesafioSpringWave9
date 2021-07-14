package com.example.desafio_spring.repositories.interfaces;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.ProductRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;

import java.text.ParseException;
import java.util.Map;

public interface ISocialMeliRepository{

    Map<Integer, User> getUsers();
    Integer saveUser(UserRequestDTO userRequestDTO);
    Integer saveUserByUser(User user);
    Integer getUserByUser(String userName);
    User getUserById(Integer userId);
    //metodos publicaciones
    Map<Integer, Post> getPosts();
    Integer savePost(PostRequestDTO postRequestDTO) throws ParseException;
    Integer getPostById(Integer postId);
}
