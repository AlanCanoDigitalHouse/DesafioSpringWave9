package com.example.desafiospring.service;

import com.example.desafiospring.dtos.FollowDTO;
import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.PromoPostDTO;
import com.example.desafiospring.dtos.request.PostRequestDTO;
import com.example.desafiospring.dtos.request.PromoPostRequestDTO;
import com.example.desafiospring.dtos.response.PostResponseDTO;
import com.example.desafiospring.dtos.response.UserPostsResponseDTO;
import com.example.desafiospring.exceptions.UserNotExistsException;

import java.util.List;

public interface IPostService {

    PostDTO loadPost(PostRequestDTO pPostRequestDTO) throws UserNotExistsException;

    PromoPostDTO loadPromoPost(PromoPostRequestDTO pPostRequestDTO) throws UserNotExistsException;

    List<PostResponseDTO> getPostsByUser(FollowDTO pFollow);

    UserPostsResponseDTO getPromoPosts(int pUserId) throws UserNotExistsException;

}
