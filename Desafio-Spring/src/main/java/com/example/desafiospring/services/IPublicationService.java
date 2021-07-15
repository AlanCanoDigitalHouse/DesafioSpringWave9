package com.example.desafiospring.services;


import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.dtos.response.FollowerPostListDTO;
import com.example.desafiospring.exceptions.UserNotFindOrEqualException;
import org.springframework.http.ResponseEntity;

public interface IPublicationService {

    ResponseEntity<?> newPost(Integer userId, PublicationRequestDTO publicationRequestDTO) throws UserNotFindOrEqualException;

    FollowerPostListDTO getAllPost(Integer userId, String mode) throws UserNotFindOrEqualException;
}
