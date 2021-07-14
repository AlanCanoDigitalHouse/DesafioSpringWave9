package com.example.desafio_spring.services.interfaces;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.UserAlreadyFollowedException;
import com.example.desafio_spring.exceptions.UserNotExistException;

import java.text.ParseException;
import java.util.Map;

public interface ISocialMeliService {
    User saveUser(UserRequestDTO userRequestDTO);
    Map<Integer, User> getAllUsers();
    void Follow(Integer id, Integer idToFollow) throws UserAlreadyFollowedException;
    User FollwerCount(Integer userId) throws UserNotExistException;
    User FollowersList(Integer userId) throws UserNotExistException;
    User FollwedList(Integer userId) throws UserNotExistException;
    //Metodos para publicaciones
    Post savePost(PostRequestDTO postRequestDTO) throws ParseException;
}
