package com.example.desafiospring.repository;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.PromoPostDTO;
import com.example.desafiospring.dtos.request.PostRequestDTO;
import com.example.desafiospring.dtos.request.PromoPostRequestDTO;
import com.example.desafiospring.dtos.response.PostResponseDTO;

import java.util.List;

public interface IPostRepository {

    PostDTO loadPostDB(PostRequestDTO pPostRequestDTO);

    PromoPostDTO loadPromoPostDB(PromoPostRequestDTO pPostRequestDTO);

    List<PostResponseDTO> getPostLastTwoWeeks(int pUserId);

    List<PromoPostDTO> getPromoPostsById(int userId);

}
