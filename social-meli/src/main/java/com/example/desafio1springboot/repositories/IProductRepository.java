package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;

import java.util.List;

public interface IProductRepository {
    void addNewPost(PostDTO post) throws PostNotValidDateException;
    UserPostsResposeDTO<PostResponseDTO> listsPostsFromUser_(Integer userId);
    void addNewPromoPost(PostInPromoDTO post) throws PostNotValidDateException;
    UserSellerResponseDTO postPromoMeBy_(Integer userId, String userName);
    List<PostInPromoDTO> getPromoPostByUser_(Integer userId);
}
