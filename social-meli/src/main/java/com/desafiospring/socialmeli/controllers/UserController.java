package com.desafiospring.socialmeli.controllers;

import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.services.IUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUser userService;

    public UserController(IUser userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow, HttpServletResponse response) throws UserException {
        this.userService.addFollower(userId, userIdToFollow);
    }

}
