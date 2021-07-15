package com.meli.itbootcamp.services;

import com.meli.itbootcamp.dto.CountPromoDTO;
import com.meli.itbootcamp.dto.ListPostSellerDTO;
import com.meli.itbootcamp.dto.ListPromoSellerDTO;
import com.meli.itbootcamp.dto.ResponseDTO;
import com.meli.itbootcamp.dto.request.PostRequestDTO;
import com.meli.itbootcamp.dto.request.PromoRequestDTO;
import com.meli.itbootcamp.exceptions.PostException;
import com.meli.itbootcamp.exceptions.UserException;

public interface PostServices {
    ResponseDTO createNewPost(PostRequestDTO newPost) throws PostException;
    ListPostSellerDTO getPostSeller(Integer nonSeller, String orderBy) throws UserException;
    ResponseDTO createNewPromo(PromoRequestDTO newPromo) throws UserException;
    CountPromoDTO quantPromo(Integer seller);
    ListPromoSellerDTO getPromoSeller(Integer seller, String orderBy);



}
