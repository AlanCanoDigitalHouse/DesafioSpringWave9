package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowedLatestPostsResponseDTO;
import com.mercadolibre.socialmeli.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Point 5: Make a new Post and sets the Status to OK if everything was correct
     */
    @PostMapping("/products/newpost")
    public void addPost(HttpServletResponse response, @RequestBody @Valid PostRequestDTO postRequestDTO) {
        this.postService.addPost(postRequestDTO);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Point 6 y 9: Returns in JSON a list of the publications made by the sellers that a user follows in the last two weeks,
     *
     * @param userId
     * @param order  = date_desc (default)
     * @param order  = date_asc
     * @return UserFollowedLatestPostsResponseDTO And Status OK
     */
    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<UserFollowedLatestPostsResponseDTO> followedLatestPosts(@PathVariable Integer userId, @RequestParam(defaultValue = "date_desc") String order) {
        UserFollowedLatestPostsResponseDTO responseDTO = this.postService.followedLatestPosts(userId, order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
