package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.dto.request.UserRequestDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowersListDTO;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFoundException;
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
    public void createAll(HttpServletResponse response) throws ApplicationException {
        this.userService.createAll(response);

    }

    @PutMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(this.userService.create(userRequestDTO), HttpStatus.OK);
    }


    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable int userId, @PathVariable int userIdToFollow, HttpServletResponse response)
            throws ApplicationException {
        this.userService.follow(userId, userIdToFollow, response);
    }

    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity<FollowersCountDTO> followersCount(@PathVariable int userId)
            throws UserNotFoundException {

        return new ResponseEntity<>(this.userService.getFollowersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{UserID}/followers/list")
    public ResponseEntity<FollowersListDTO> followers(@PathVariable int UserID) throws UserNotFoundException {
        return new ResponseEntity<>(this.userService.getFollowers(UserID), HttpStatus.OK);
    }

    @GetMapping("/users/{UserID}/followed/list")
    public ResponseEntity<FollowedListDTO> followed(@PathVariable int UserID) throws UserNotFoundException {
        return new ResponseEntity<>(this.userService.getFollowed(UserID), HttpStatus.OK);
    }

}
