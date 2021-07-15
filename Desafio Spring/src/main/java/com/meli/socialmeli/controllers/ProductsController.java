package com.meli.socialmeli.controllers;

import com.meli.socialmeli.dtos.request.NewpostDTO;
import com.meli.socialmeli.dtos.response.FollowedPostListDTO;
import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.services.PostsService;
import com.meli.socialmeli.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final PostsService postsService;
    private final UsersService usersService;

    public ProductsController(UsersService usersService, PostsService postsService){
        this.postsService = postsService;
        this.usersService = usersService;
    }

    @GetMapping("/followed/{userId}/list")
    ResponseEntity<FollowedPostListDTO> getFollowedPost(@PathVariable int userId, @RequestParam(required = false) String order) throws UserDoesNotExistException{
        FollowedPostListDTO list = new FollowedPostListDTO(usersService.getUser(userId), postsService.getFollowedPostings(usersService.getFollowing(userId, order), order));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/newpost")
    ResponseEntity<String> newPost(@Valid @RequestBody NewpostDTO post) throws UserDoesNotExistException{
        if(usersService.validateUserPosting(post.getUserId())){ postsService.posting(post); }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }


}
