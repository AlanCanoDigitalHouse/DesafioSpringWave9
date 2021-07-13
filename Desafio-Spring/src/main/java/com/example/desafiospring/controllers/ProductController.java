package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import com.example.desafiospring.services.PublicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    private final PublicationServiceImpl publicationService;

    public ProductController(PublicationServiceImpl publicationService) {
        this.publicationService = publicationService;
    }


    //005
    @PostMapping(path = "/newpost")
    public ResponseEntity newPost(@RequestBody PublicationRequestDTO publicationRequestDTO) throws UserNotFindException {
        System.out.println("newPost");
        return publicationService.newPost(publicationRequestDTO.getUserId(), publicationRequestDTO);
    }
}
