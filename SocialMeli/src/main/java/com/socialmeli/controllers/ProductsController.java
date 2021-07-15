package com.socialmeli.controllers;

import com.socialmeli.dtos.ResponseDTO;
import com.socialmeli.dtos.request.PostRequestDTO;
import com.socialmeli.dtos.request.SortEnum;
import com.socialmeli.services.IProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final IProductsService productsService;

    /**
     * Constructor with dependency injections service product
     * @param productsService service product
     */
    public ProductsController(IProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     *Calls the service that adds a new post to SocialMeli.
     * @param post new post to add
     * @return OK status if everything was executed correctly
     */
    @PostMapping("/newpost")
    public ResponseEntity<ResponseDTO> addPost(@Valid @RequestBody PostRequestDTO post) {
        return new ResponseEntity<>(productsService.addPostSocial(post), HttpStatus.OK);
    }

    /**
     * Calls the service that lists the posts published by the vendors followed by a user.
     * @param userId id of the user to return the posts of his followed sellers
     * @param order ordering of posts by date
     * @return OK status and the list of posts if everything was executed correctly
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ResponseDTO> listFollowedPost(@PathVariable Integer userId, @RequestParam(defaultValue = "date_asc", required = false) SortEnum order) {
        return new ResponseEntity<>(productsService.listPostFollowed(userId, order), HttpStatus.OK);
    }

    /**
     * Calls the service that lists all published posts.
     * @return OK status and the list of posts if everything was executed correctly
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> listPost() {
        return new ResponseEntity<>(productsService.listPosts(), HttpStatus.OK);
    }
}
