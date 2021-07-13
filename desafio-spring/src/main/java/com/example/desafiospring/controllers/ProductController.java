package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.PostCreateDto;
import com.example.desafiospring.dtos.PostDto;
import com.example.desafiospring.dtos.PostFollowedDto;
import com.example.desafiospring.exceptions.DateInvalidException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.services.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private IPostService postService;

    public ProductController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostCreateDto postDto)
            throws DateInvalidException, UserNotExistException {
        return ResponseEntity.ok(this.postService.createPost(postDto));
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostFollowedDto> getRecentPosts(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @RequestParam(required = false, defaultValue = "date_asc") @NotBlank String order)
            throws UserNotExistException {
        return ResponseEntity.ok(this.postService.getRecentPosts(userId, order));
    }

}
