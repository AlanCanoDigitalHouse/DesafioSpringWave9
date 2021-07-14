package com.example.desafiospring.controllers;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.services.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    IPostService postService;

    public ProductController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public void newPost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, ProductException, SellerException {
        this.postService.savePost(postRequestDTO);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> postFollowed(@PathVariable @NotBlank Long userId, @RequestParam(defaultValue = "id") @Nullable String order) throws PostException, ProductException, SellerException {
        List<PostResponseDTO> responseDTOList = this.postService.getFollowedPostsByUser(userId, order);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }


    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<CountPromoPostsResponseDTO> countPromoPosts(@PathVariable @NotBlank Long userId) throws PostException, ProductException, SellerException {
        CountPromoPostsResponseDTO responseDTO = this.postService.countPromoPostsPerSeller(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public void newPromoPost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, ProductException, SellerException {
        this.postService.savePost(postRequestDTO);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> getPromoProducts(@PathVariable @NotBlank Long userId) throws SellerException {
        List<PostResponseDTO> responseDTOList = this.postService.getPromoPostsPerSeller(userId);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

}
