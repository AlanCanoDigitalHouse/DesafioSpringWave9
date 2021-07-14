package com.example.desafiospring.services;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.models.Post;

import java.util.List;

public interface IPostService {
    Post getPost(Long id) throws PostException;

    void savePost(PostRequestDTO postRequestDTO) throws PostException, ProductException, SellerException;

    List<PostResponseDTO> getFollowedPostsByUser(Long userId, String order);

    CountPromoPostsResponseDTO countPromoPostsPerSeller(Long userId) throws SellerException;

    List<PostResponseDTO> getPromoPostsPerSeller(Long userId) throws SellerException;
}
