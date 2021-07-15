package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.request.NewPostRequest;
import com.mercadolibre.socialmeli.dto.response.UserIdPostResponse;
import com.mercadolibre.socialmeli.entity.Post;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface IPostService {

    public ResponseEntity createPost(NewPostRequest post);
    public UserIdPostResponse listProductsFollowed(Integer Id, String order) throws ParseException;

}
