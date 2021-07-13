package com.desafiospring.socialmeli.controllers;

import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.services.IUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerFollowersCountDTO> getFollowersCount(@PathVariable Integer userId) throws UserException {
        SellerFollowersCountDTO sellerFollowersCountDTO = userService.getFollowersCount(userId);
        return new ResponseEntity<>(sellerFollowersCountDTO, HttpStatus.OK);
    }

}
