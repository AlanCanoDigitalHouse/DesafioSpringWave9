package com.meli.joescaos.socialmeli.socialmeli.controllers;

import com.meli.joescaos.socialmeli.socialmeli.dtos.requests.PostRequestDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.PostsListDto;
import com.meli.joescaos.socialmeli.socialmeli.services.BuyerService;
import com.meli.joescaos.socialmeli.socialmeli.services.PostService;
import com.meli.joescaos.socialmeli.socialmeli.services.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author jocanas
 */

@RestController
// Entry endpoint for products services
@RequestMapping("/products")
public class ProductsController {


    /*
     * Controller attributes
     */
    private BuyerService buyerService;
    private SellerService sellerService;
    private PostService postService;

    /**
     *
     * @param buyerService
     * @param sellerService
     * @param postService
     */
    public ProductsController(BuyerService buyerService, SellerService sellerService, PostService postService) {
        this.buyerService = buyerService;
        this.sellerService = sellerService;
        this.postService = postService;
    }

    /**
     *
     * @param newPost
     * @return Response Entity with Htttp Status
     */
    @PostMapping("/newpost")
    public ResponseEntity createPost( @Valid @RequestBody PostRequestDto newPost) {
        postService.createPost(newPost);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     *
     * @param userId
     * @param order
     * @return Response entity with a PostListDto as response and Http Status
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsListDto> getPostsList(@PathVariable int userId,
                                                     @RequestParam(required = false) String order) {
        return new ResponseEntity(buyerService.getPostsList(userId, order), HttpStatus.OK);
    }

}
