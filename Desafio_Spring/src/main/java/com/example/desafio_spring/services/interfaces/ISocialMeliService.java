package com.example.desafio_spring.services.interfaces;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.PostResponseByUserDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.*;

import java.text.ParseException;
import java.util.Map;

public interface ISocialMeliService {
    User saveUser(UserRequestDTO userRequestDTO);
    Map<Integer, User> getAllUsers();
    void Follow(Integer id, Integer idToFollow) throws UserAlreadyFollowedException, UserNotExistException, FollowSameUserException;
    void unFollow(Integer id, Integer idToUnFollow);
    User FollwerCount(Integer userId) throws UserNotExistException;
    boolean validateFollowUser(Integer userId, Integer useIdToFollow) throws UserAlreadyFollowedException;
    User FollowersList(Integer userId) throws UserNotExistException;
    User FollowersListSorted(String order, Integer userId) throws UserNotExistException;
    User FollwedList(Integer userId) throws UserNotExistException;
    User FollwedListSorted(String order, Integer userId) throws UserNotExistException, InvalidInputVariableException;
    //Metodos para publicaciones
    Post savePost(PostRequestDTO postRequestDTO) throws ParseException;
    Map<Integer, Post> getAllPosts();

    PostResponseByUserDTO getPostByUserid(Integer userId) throws ParseException;
    PostResponseByUserDTO getPostByUseridSorted(String order, Integer userId) throws PostNotExistException, InvalidInputVariableException;
}
