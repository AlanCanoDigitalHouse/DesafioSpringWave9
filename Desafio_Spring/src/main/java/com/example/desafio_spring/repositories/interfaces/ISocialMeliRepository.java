package com.example.desafio_spring.repositories.interfaces;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public interface ISocialMeliRepository{

    Map<Integer, User> getUsers();
    Integer saveUser(UserRequestDTO userRequestDTO);
    Integer saveUserByUser(User user);
    Integer getUserByUser(String userName);
    User getUserById(Integer userId);
    //metodos publicaciones
    Map<Integer, Post> getPosts();
    ArrayList<Post> getPostList();
    Integer savePost(PostRequestDTO postRequestDTO) throws ParseException;
    Integer getPostById(Integer postId);
    Integer saveListPost(PostRequestDTO postRequestDTO) throws ParseException;
    boolean isFollowedBy(Integer userId, Integer userIdToFollow);
    //metodos productos
}
