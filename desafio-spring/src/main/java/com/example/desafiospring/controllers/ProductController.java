package com.example.desafiospring.controllers;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.services.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {
    IPostService postService;

    public ProductController(IPostService postService) {
        this.postService = postService;
    }

    /**
     * US 0005 POST /products/newpost
     * Creates a new post
     *
     * @param postRequestDTO New post data in the body
     * @throws SellerException Seller exception in case that the seller not exists
     */
    @PostMapping("/newpost")
    public void newPost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws SellerException {
        this.postService.savePost(postRequestDTO);
    }

    /**
     * US 0006 GET /products/followed/{userId}/list
     * Las two weeks post from a user followed sellers
     * If theres'no results then return an empty posts array
     *
     * @param userId user id to retrieve the followed posts
     * @param order  order of results, date_asc or date_desc (default if null)
     * @return ResponseEntity<List < PostResponseDTO>>
     * @throws UserException Exception in case user doesn't exist
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> postFollowed(
            @Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Integer userId,
            @RequestParam(defaultValue = "date_desc") @Nullable String order) throws UserException {
        List<PostResponseDTO> responseDTOList = this.postService.getFollowedPostsByUser(userId, order);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

    /**
     * US 0010 /products/newpromopost
     * Creates a new promo post
     *
     * @param postRequestDTO data in the request body
     * @throws PostException   post exception if the promo post fields are null
     * @throws SellerException seller exception in case the seller id in field doesn't belong to any seller
     */
    @PostMapping("/newpromopost")
    public void newPromoPost(
            @Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, SellerException {
        this.postService.savePromoPost(postRequestDTO);
    }

    /**
     * US 0011 /products/{userId}/countPromo
     * Returns the number of promo posts belonging to an user
     *
     * @param userId id of the user which promo posts to retrieve
     * @return ResponseEntity<>(responseDTO, HttpStatus.OK)
     * @throws SellerException Exception in case of Seller doesn't exist
     */
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<CountPromoPostsResponseDTO> countPromoPosts(
            @Valid @PathVariable @Min(value = 1, message = "Seller Id has to be a number greater than 0") Integer userId) throws SellerException {
        CountPromoPostsResponseDTO responseDTO = this.postService.countPromoPostsPerSeller(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    /**
     * US 0012 /products/{userId}/list
     * Return a list with the promo posts of a seller
     *
     * @param userId id of the seller
     * @return ResponseEntity<List < PostResponseDTO>>
     * @throws SellerException in case that seller doesn't exists
     */
    @GetMapping("/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> getPromoProducts(
            @Valid @PathVariable @Min(value = 1, message = "Seller Id has to be a number greater than 0") Integer userId,
            @RequestParam(defaultValue = "date_desc") @Nullable String order) throws SellerException {
        List<PostResponseDTO> responseDTOList = this.postService.getPromoPostsPerSeller(userId, order);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

}
