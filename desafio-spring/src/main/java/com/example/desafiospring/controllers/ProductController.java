package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.exceptions.DateInvalidException;
import com.example.desafiospring.exceptions.DiscountInvalidException;
import com.example.desafiospring.exceptions.PromoPostInvalidException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.services.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

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
            throws DateInvalidException, UserNotExistException, IOException {
        return ResponseEntity.ok(this.postService.createPost(postDto));
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<PostDto> createPromoPost(@Valid @RequestBody PostPromoCreateDto postDto)
            throws DateInvalidException, UserNotExistException, PromoPostInvalidException, IOException, DiscountInvalidException {
        return ResponseEntity.ok(this.postService.createPromoPost(postDto));
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostFollowedDto> getRecentPosts(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @RequestParam(required = false, defaultValue = ConstantEnum.ORDER_DATE_DESC) @NotBlank String order)
            throws UserNotExistException, IOException {
        return ResponseEntity.ok(this.postService.getRecentPosts(userId, order));
    }

    @GetMapping("/{userId}/countPromo/")
    public ResponseEntity<UserPostDto> countPostsByUserId(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId)
            throws UserNotExistException, IOException {
        return ResponseEntity.ok(this.postService.countPostsByUserId(userId));
    }

    @GetMapping("/{userId}/list/")
    public ResponseEntity<UserPostDto> getPostsByUserId(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @RequestParam(required = false, defaultValue = ConstantEnum.ORDER_DATE_DESC) @NotBlank String order)
            throws UserNotExistException, IOException {
        return ResponseEntity.ok(this.postService.getPostsByUserId(userId, order));
    }

}
