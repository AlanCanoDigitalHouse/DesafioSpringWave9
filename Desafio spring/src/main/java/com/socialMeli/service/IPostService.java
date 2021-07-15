package com.socialMeli.service;

import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.dto.response.CountPromoPostsResponseDTO;
import com.socialMeli.dto.response.ProductsSellersThatUserFollowsDTO;
import com.socialMeli.dto.response.PromoPostsOfAUserResponseDTO;
import com.socialMeli.exception.exception.DateNotValidException;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.exception.exception.OrderNotValidException;

import javax.validation.Valid;
import java.text.ParseException;

public interface IPostService {
    void addNewPost(@Valid PostInfoToCreateDTO dataPost) throws ParseException, ModelNotExists, ModelAlreadyExists, DateNotValidException;

    ProductsSellersThatUserFollowsDTO postSellersThatUserFollows(int userId, String order) throws ModelNotExists, OrderNotValidException;

    CountPromoPostsResponseDTO countPromoPosts(int userId) throws ModelNotExists;

    PromoPostsOfAUserResponseDTO getPromoPostsLists(int userId) throws ModelNotExists;
}
