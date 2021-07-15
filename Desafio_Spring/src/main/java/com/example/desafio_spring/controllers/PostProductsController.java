package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.request.PostPromoRequestDTO;
import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.response.PostResponseByUserDTO;
import com.example.desafio_spring.dtos.response.PostResponsePromoByUserDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.InvalidInputVariableException;
import com.example.desafio_spring.exceptions.PostNotExistException;
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
    @PostMapping("/newpromopost")
    public void savePostPromo(@RequestBody PostPromoRequestDTO postPromoRequestDTO, HttpServletResponse response) throws ParseException {
        iSocialMeliService.savePostPromo(postPromoRequestDTO);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<Map<Integer, Post>> getAllPosts(){
        return ResponseEntity.ok(iSocialMeliService.getAllPosts());
    }
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostResponseByUserDTO> getPostByUser(@PathVariable(value = "userId") Integer userId, @RequestParam(required = false, defaultValue = "date_desc") String order) throws PostNotExistException, InvalidInputVariableException {
        return ResponseEntity.ok(iSocialMeliService.getPostByUseridSorted(order, userId));
    }
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<User> countPromo(@PathVariable(value = "userId") Integer userId){
        return ResponseEntity.ok(iSocialMeliService.PostPromoCount(userId));
    }
    @GetMapping("/{userId}/list")
    public ResponseEntity<PostResponsePromoByUserDTO> getPromoPost(@PathVariable(value = "userId") Integer userId) throws PostNotExistException, InvalidInputVariableException {
        return ResponseEntity.ok(iSocialMeliService.getPostByUserId(userId));
    }


}
