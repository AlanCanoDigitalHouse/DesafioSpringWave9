package com.desafiospring.socialmeli.controllers;

import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.dtos.requests.PostRequestDTO;
import com.desafiospring.socialmeli.dtos.responses.PostListDto;
import com.desafiospring.socialmeli.dtos.responses.PromoPostCountDTO;
import com.desafiospring.socialmeli.dtos.responses.PromoPostsDTO;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.services.IProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProduct productService;

    public ProductController(IProduct productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> newPost(@Valid @RequestBody PostRequestDTO post) throws UserException {
        Post postResponse = productService.addPost(post);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostListDto> getFollowedSellersPosts(@PathVariable Integer userId,
                                                               @RequestParam(defaultValue = "date_desc") String order) throws UserException{
        PostListDto postListDto = productService.getFollowedSellersPost(userId, order);
        return new ResponseEntity<>(postListDto, HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<Post> newPromoPost(@Valid @RequestBody PostRequestDTO post) throws UserException {
        Post postResponse = productService.addPost(post);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<PromoPostCountDTO> getCountPromoPosts(@PathVariable Integer userId) throws UserException{
        PromoPostCountDTO promoPostCountDTO = productService.getCountPromoPosts(userId);
        return new ResponseEntity<>(promoPostCountDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<PromoPostsDTO> getPromoPosts(@PathVariable Integer userId,
                                                       @RequestParam(defaultValue = "") String order) throws UserException{
        PromoPostsDTO promoPostsDTO = productService.getPromoPosts(userId, order);
        return new ResponseEntity<>(promoPostsDTO, HttpStatus.OK);
    }


}
