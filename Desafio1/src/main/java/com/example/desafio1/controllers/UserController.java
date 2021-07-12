package com.example.desafio1.controllers;

import com.example.desafio1.dto.User;
import com.example.desafio1.exceptions.UserNotFoundException;
import com.example.desafio1.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(value = HttpStatus.OK)
    public void follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        try{
            userService.follow(userId, userIdToFollow);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad bad");
        }

    }
}
