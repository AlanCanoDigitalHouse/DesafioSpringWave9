package com.meli.socialmeli.controllers;

import com.meli.socialmeli.dtos.response.FollowersCountDTO;
import com.meli.socialmeli.dtos.response.FollowersUserListDTO;
import com.meli.socialmeli.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService){ this.usersService = usersService; }

    @GetMapping("/followers/list")
    ResponseEntity<FollowersUserListDTO> getFollowers(@PathVariable int userId) throws Exception{
        FollowersUserListDTO followers = usersService.getFollowers(userId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @GetMapping("/followers/count")
    ResponseEntity<FollowersCountDTO> countFollowers(@PathVariable int userId) throws Exception{
        FollowersCountDTO count = usersService.countFollowers(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping("/follow/{userIdToFollow}")
    ResponseEntity<String> follow(@PathVariable int userId, @PathVariable int userIdToFollow) throws Exception {
        usersService.follow( userIdToFollow, userId);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
