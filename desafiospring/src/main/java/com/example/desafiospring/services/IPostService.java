package com.example.desafiospring.services;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.responsedtos.SellerWithPostsDTO;
import com.example.desafiospring.exceptions.*;

public interface IPostService {

    void createNewPost(PostDTO postDTO) throws UserNotFoundByIdException, PostNotFoundByIdException, PostAlreadyExistsException, ProductAlreadyExistsException;

    SellerWithPostsDTO getPostFromSeller(Integer sellerId, String order) throws UserNotFoundByIdException, OrderNotExistsException;

}
