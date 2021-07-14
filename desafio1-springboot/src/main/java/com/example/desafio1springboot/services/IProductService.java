package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.*;

import java.util.List;

public interface IProductService {
    void createNewPost(PostDTO post) throws UserSellerNotFoundExceptions, PostNotValidDateException;
    UserPostsResposeDTO listsPostsFromUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
    void addNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException;
    UserSellerResponseDTO countProductsInPromo(Integer userId) throws UserSellerNotFoundExceptions;
    List<PostDTO> show();

    UserPostsResposeDTO getPostsInPromoByUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
}
