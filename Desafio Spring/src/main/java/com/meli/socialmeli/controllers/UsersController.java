package com.meli.socialmeli.controllers;

import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userId}")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService){ this.usersService = usersService; }

    @PostMapping("/follow/{userIdToFollow}")
    ResponseEntity<String> follow(@PathVariable int userId, @PathVariable int userIdToFollow) throws Exception{
        try{usersService.follow( userIdToFollow, userId); }
        catch (Exception e) { throw e; }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
