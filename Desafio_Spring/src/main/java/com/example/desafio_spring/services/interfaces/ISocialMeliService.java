package com.example.desafio_spring.services.interfaces;

import com.example.desafio_spring.dtos.request.PostPromoRequestDTO;
import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.PostResponseByUserDTO;
import com.example.desafio_spring.dtos.response.PostResponsePromoByUserDTO;
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
    User PostPromoCount(Integer userId);
    boolean validateFollowUser(Integer userId, Integer useIdToFollow) throws UserAlreadyFollowedException;
    User FollowersListSorted(String order, Integer userId) throws UserNotExistException, InvalidInputVariableException;
    User FollwedListSorted(String order, Integer userId) throws UserNotExistException, InvalidInputVariableException;
    //Metodos para publicaciones
    Post savePost(PostRequestDTO postRequestDTO) throws ParseException;
    Post savePostPromo(PostPromoRequestDTO postPromoRequestDTO) throws ParseException;
    Map<Integer, Post> getAllPosts();
    PostResponseByUserDTO getPostByUseridSorted(String order, Integer userId) throws PostNotExistException, InvalidInputVariableException;

    PostResponsePromoByUserDTO getPostByUserId(Integer userId);
}
