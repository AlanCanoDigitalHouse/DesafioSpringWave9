package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.*;

public interface IProductService {
    void createNewPost(PostDTO post) throws UserSellerNotFoundExceptions, PostNotValidDateException;
    void createNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException;
    UserPostsResposeDTO listsPostsFromUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
    UserSellerResponseDTO countProductsInPromo(Integer userId) throws UserSellerNotFoundExceptions;
    UserPostsResposeDTO getPostsInPromoByUser_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException;
}
