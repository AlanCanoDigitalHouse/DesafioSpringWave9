package com.example.desafiospring.services;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;

import java.util.List;

public interface IPostService {

    void savePost(PostRequestDTO postRequestDTO) throws PostException, ProductException, SellerException;

    void savePromoPost(PostRequestDTO postRequestDTO) throws PostException, SellerException;

    List<PostResponseDTO> getFollowedPostsByUser(Long userId, String order) throws UserException;

    CountPromoPostsResponseDTO countPromoPostsPerSeller(Long userId) throws SellerException;

    List<PostResponseDTO> getPromoPostsPerSeller(Long sellerId) throws SellerException;
}
