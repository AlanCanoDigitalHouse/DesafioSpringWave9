package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.request.PostPromoRequestDTO;
import com.mercadolibre.socialmeli.dto.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostCountPromoResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseDTO;
import com.mercadolibre.socialmeli.exception.InvalidRequestParamException;
import com.mercadolibre.socialmeli.service.PostService;
import com.mercadolibre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class PostController {

    PostService postService;
    UserService userService;

    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    /**
     *
     * @param postRequestDTO
     * @return
     */
    @PostMapping("/products/newpost")
    @ResponseStatus(HttpStatus.OK)
    public String newPost(
            @Valid @RequestBody PostRequestDTO postRequestDTO
            ){
        int postId = postService.newPost(postRequestDTO);
        userService.addPostToUser(postRequestDTO.getUserId(), postId);
        return "post successfully created";
    }

    /**
     *
     * @param postPromoRequestDTO
     * @return
     */
    @PostMapping("/products/newpromopost")
    @ResponseStatus(HttpStatus.OK)
    public String newPostPromo(
            @Valid @RequestBody PostPromoRequestDTO postPromoRequestDTO
    ){
        int postId = postService.newPostPromo(postPromoRequestDTO);
        userService.addPostToUser(postPromoRequestDTO.getUserId(), postId);
        return "post successfully created";
    }

    /**
     * @param userId
     * @param order
     * @return
     */
    @GetMapping("/products/followed/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDTO getPostsFollowed(
            @PathVariable int userId,
            @RequestParam Optional<String> order){
        List<String> validOrderParameters =  Arrays.asList("date_asc", "date_desc");
        if (!validOrderParameters.contains(order.orElse("date_asc"))) throw new InvalidRequestParamException(order.get(), validOrderParameters);
        return postService.getPostsFollowed(userId, order.orElse("date_asc"));
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/products/{userId}/list/")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDTO getPostsWithPromoByUser(
            @PathVariable int userId){
        return postService.getPostsWithPromoByUser(userId);
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/products/{userId}/countPromo/")
    @ResponseStatus(HttpStatus.OK)
    public PostCountPromoResponseDTO getPostsPromoCount(
            @PathVariable int userId) {
        return postService.getPostsPromoCount(userId);
    }
}
