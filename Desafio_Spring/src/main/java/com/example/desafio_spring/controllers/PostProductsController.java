package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.PostResponseByUserDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.InvalidInputVariableException;
import com.example.desafio_spring.exceptions.PostNotExistException;
import com.example.desafio_spring.exceptions.UserNotExistException;
import com.example.desafio_spring.services.interfaces.ISocialMeliService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class PostProductsController {
    ISocialMeliService iSocialMeliService;

    public PostProductsController(ISocialMeliService iSocialMeliService) {
        this.iSocialMeliService = iSocialMeliService;
    }
    @PostMapping("/newpost")
    public void savePost(@RequestBody PostRequestDTO postRequestDTO, HttpServletResponse response) throws ParseException {
        iSocialMeliService.savePost(postRequestDTO);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<Map<Integer, Post>> getAllPosts(){
        return ResponseEntity.ok(iSocialMeliService.getAllPosts());
    }
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostResponseByUserDTO> getPostByUser(@PathVariable(value = "userId") Integer userId, @RequestParam(required = false, defaultValue = "date_desc") String order) throws ParseException, PostNotExistException, InvalidInputVariableException {
        //iSocialMeliService.getPostByUserid(userId);
        return ResponseEntity.ok(iSocialMeliService.getPostByUseridSorted(order, userId));
    }

}
