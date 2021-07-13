package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.PostDto;
import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.dtos.responses.ResponsePostsListDto;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.services.ISocialMeliServices;
import com.example.socialmeli.services.SocialMeliServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController

public class SocialMeliController {

    ISocialMeliServices socialMeliServices;

    public SocialMeliController(SocialMeliServices s){
        this.socialMeliServices = s;
    }

    @PostMapping("/init")
    public void init(HttpServletResponse response){
        socialMeliServices.init();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public void follow(HttpServletResponse response, @PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws UserNotFound {
        socialMeliServices.follow(response,userId,userIdToFollow);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollow(HttpServletResponse response, @PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws UserNotFound {
        socialMeliServices.unfollow(response,userId,userIdToUnfollow);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<ResponseCantFollowersDto> followersCount(HttpServletResponse response, @PathVariable Integer userId) throws UserNotFound {
        return new ResponseEntity<>(socialMeliServices.getFollowersCount(response,userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<ResponseFollowersDto> followersInfo(HttpServletResponse response,
                                                              @PathVariable Integer userId,
                                                              @RequestParam(defaultValue="none") String order) throws UserNotFound {
        return new ResponseEntity<>(socialMeliServices.getFollowersInfo(response,userId,order), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<ResponseFollowedDto> followedInfo(HttpServletResponse response,
                                                            @PathVariable Integer userId,
                                                            @RequestParam(defaultValue = "none") String order) throws UserNotFound {
        return new ResponseEntity<>(socialMeliServices.getFollowedInfo(response,userId,order), HttpStatus.OK);
    }

    @PostMapping("/products/newpost")
    public void post(HttpServletResponse response, @RequestBody PostDto postDto) throws UserNotFound {
        socialMeliServices.post(response, postDto);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<ResponsePostsListDto> postsInfo(HttpServletResponse response,
                                                          @PathVariable Integer userId,
                                                          @RequestParam(defaultValue = "none") String order) throws UserNotFound {
        return new ResponseEntity<>(socialMeliServices.getPostsInfo(response,userId,order), HttpStatus.OK);
    }
}
