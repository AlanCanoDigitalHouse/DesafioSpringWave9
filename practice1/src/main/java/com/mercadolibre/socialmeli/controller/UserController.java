package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.dto.response.FollowedResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersResponseDTO;
import com.mercadolibre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> allUsers() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> getFollowersCount(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("{userId}/followers/list")
    public ResponseEntity<FollowersResponseDTO> getFollowers(@PathVariable Integer userId,
                                                             @RequestParam(required = false) String order) {
        FollowersResponseDTO response;
        if (order != null)
            response = userService.getFollowers(userId, order);
        else
            response = userService.getFollowers(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{UserID}/followed/list")
    public ResponseEntity<FollowedResponseDTO> getFollowed(@PathVariable Integer UserID,
                                                           @RequestParam(required = false) String order) {
        FollowedResponseDTO response;
        if (order != null)
            response = userService.getFollowed(UserID, order);
        else
            response = userService.getFollowed(UserID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("{userId}/follow/{userIdToFollow}")
    public HttpStatus follow(@PathVariable int userId, @PathVariable int userIdToFollow) {
        userService.addFollower(userId, userIdToFollow);
        return HttpStatus.OK;
    }

    @PostMapping("{userId}/unfollow/{userIdToFollow}")
    public HttpStatus unfollow(@PathVariable int userId, @PathVariable int userIdToFollow) {
        userService.unFollow(userId, userIdToFollow);
        return HttpStatus.OK;
    }
}
