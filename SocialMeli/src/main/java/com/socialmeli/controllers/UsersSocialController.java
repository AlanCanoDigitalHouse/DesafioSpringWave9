package com.socialmeli.controllers;

import com.socialmeli.dtos.UserDTO;
import com.socialmeli.dtos.response.FollowCountDTO;
import com.socialmeli.dtos.response.FollowedListDTO;
import com.socialmeli.dtos.response.FollowerListDTO;
import com.socialmeli.services.IUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersSocialController {


    private final IUsersService usersService;

    public UsersSocialController(IUsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        usersService.followUser(userId,userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserDTO> countFollowers(@PathVariable Integer userId){
        FollowCountDTO countFollowers = usersService.countFollowers(userId);
        return new ResponseEntity<>(countFollowers, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserDTO> listFollowers(@PathVariable Integer userId){
        FollowerListDTO listFollowers = usersService.listFollowers(userId);
        return new ResponseEntity<>(listFollowers, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserDTO> listFollowed(@PathVariable Integer userId){
        FollowedListDTO listFollowed = usersService.listFollowed(userId);
        return new ResponseEntity<>(listFollowed, HttpStatus.OK);
    }

}
