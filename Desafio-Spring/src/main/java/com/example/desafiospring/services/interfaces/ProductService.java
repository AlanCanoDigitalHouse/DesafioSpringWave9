package com.example.desafiospring.services.interfaces;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;

public interface ProductService {
    NewPostResponseDTO addNewPost(NewPostRequestDTO newPostRequestDTO);
}
