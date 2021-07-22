package com.api.firstspringchallenge.services.post;

import com.api.firstspringchallenge.dtos.request.PostRequestDTO;
import com.api.firstspringchallenge.dtos.request.PostsRequestDTO;
import com.api.firstspringchallenge.dtos.request.PromoPostRequestDTO;
import org.springframework.http.ResponseEntity;

public interface PostServiceI {

    ResponseEntity newPost(PostRequestDTO post);

    ResponseEntity getPostsBy(PostsRequestDTO request);

    ResponseEntity newPromoPost(PromoPostRequestDTO post);

    ResponseEntity getPromoQuantity(int userId);

    ResponseEntity getPromoList(PostsRequestDTO request);
}
