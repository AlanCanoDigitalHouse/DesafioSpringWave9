package com.example.desafiospring.controllers;

import com.example.desafiospring.dto.request.PostRequestDTO;
import com.example.desafiospring.dto.response.CountPromoPostsResponseDTO;
import com.example.desafiospring.dto.response.PostResponseDTO;
import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.exceptions.ProductException;
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
     * @param postRequestDTO
     * @throws PostException
     * @throws ProductException
     * @throws SellerException
     */
    @PostMapping("/newpost")
    public void newPost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, ProductException, SellerException {
        this.postService.savePost(postRequestDTO);
    }

    /**
     * @param userId
     * @param order
     * @return ResponseEntity<List < PostResponseDTO>>
     * @throws PostException
     * @throws ProductException
     * @throws SellerException
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> postFollowed(
            @Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Long userId,
            @RequestParam(defaultValue = "date_desc") @Nullable String order) throws UserException {
        List<PostResponseDTO> responseDTOList = this.postService.getFollowedPostsByUser(userId, order);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }


    /**
     * @param userId
     * @return ResponseEntity<>(responseDTO, HttpStatus.OK)
     * @throws PostException
     * @throws ProductException
     * @throws SellerException
     */
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<CountPromoPostsResponseDTO> countPromoPosts(
            @Valid @PathVariable @Min(value = 1, message = "Seller Id has to be a number greater than 0") Long userId) throws SellerException {
        CountPromoPostsResponseDTO responseDTO = this.postService.countPromoPostsPerSeller(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param postRequestDTO
     * @throws PostException
     * @throws SellerException
     */
    @PostMapping("/newpromopost")
    public void newPromoPost(
            @Valid @RequestBody PostRequestDTO postRequestDTO) throws PostException, SellerException {
        this.postService.savePromoPost(postRequestDTO);
    }

    /**
     * @param userId
     * @return ResponseEntity<List < PostResponseDTO>>
     * @throws SellerException
     */
    @GetMapping("/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> getPromoProducts(
            @Valid @PathVariable @Min(value = 1, message = "Seller Id has to be a number greater than 0") Long userId) throws SellerException {
        List<PostResponseDTO> responseDTOList = this.postService.getPromoPostsPerSeller(userId);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

}
