package com.example.desafiospring.services;

import com.example.desafiospring.dtos.general.Publication;
import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPublicationService {

    ResponseEntity<?> newPost(Integer userId, PublicationRequestDTO publicationRequestDTO) throws UserNotFindException;

    List<Publication> getAllPost(Integer userId);
}
