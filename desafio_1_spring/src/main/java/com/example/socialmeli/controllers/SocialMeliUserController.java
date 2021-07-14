package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseRequestDto;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.services.ISocialMeliUserServices;
import com.example.socialmeli.services.SocialMeliUserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class SocialMeliUserController {

    ISocialMeliUserServices socialMeliUserServices;

    public SocialMeliUserController(SocialMeliUserServices s){
        this.socialMeliUserServices = s;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<ResponseRequestDto> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.follow(userId,userIdToFollow),HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<ResponseRequestDto> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.unfollow(userId,userIdToUnfollow),HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<ResponseCantFollowersDto> followersCount(@PathVariable Integer userId) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.getFollowersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<ResponseFollowersDto> followersInfo(@PathVariable Integer userId,
                                                              @RequestParam(defaultValue="none") String order) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.getFollowersInfo(userId,order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<ResponseFollowedDto> followedInfo(@PathVariable Integer userId,
                                                            @RequestParam(defaultValue = "none") String order) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.getFollowedInfo(userId,order), HttpStatus.OK);
    }

}
