package com.meli.socialmeli.controller;

import com.meli.socialmeli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public HttpStatus follow(@PathVariable(value = "userId")String userId,
                             @PathVariable(value = "userIdToFollow")String userIdFollow) {
            return userService.followUser(userId,userIdFollow);
    }

}
