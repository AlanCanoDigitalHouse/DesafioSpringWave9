package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.services.interfaces.ISocialMeliService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/products")
public class PostProductsController {
    ISocialMeliService iSocialMeliService;

    public PostProductsController(ISocialMeliService iSocialMeliService) {
        this.iSocialMeliService = iSocialMeliService;
    }
    @PostMapping("/newpost")
    public ResponseEntity<Post> savePost(@RequestBody PostRequestDTO postRequestDTO) throws ParseException {
        return ResponseEntity.ok(iSocialMeliService.savePost(postRequestDTO));
    }
}
