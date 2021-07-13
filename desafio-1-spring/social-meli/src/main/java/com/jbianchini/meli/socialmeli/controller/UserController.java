package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.dto.request.UserRequest;
import com.jbianchini.meli.socialmeli.dto.response.FollowersResponse;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFounException;
import com.jbianchini.meli.socialmeli.model.User;
import com.jbianchini.meli.socialmeli.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;



    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/createAll")
    public void createAll(HttpServletResponse response)
            throws ApplicationException {
        this.userService.createAll();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PutMapping("/create")
    public User create(@RequestBody UserRequest userRequest) {
        return this.userService.create(userRequest);
    }


    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable int userId, @PathVariable int userIdToFollow, HttpServletResponse response)
            throws ApplicationException {
        this.userService.follow(userId, userIdToFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity<FollowersResponse> followersCount(@PathVariable int userId) throws UserNotFounException {
        FollowersResponse response = this.userService.getFollowersCount(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
