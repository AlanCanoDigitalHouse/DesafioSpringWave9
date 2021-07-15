package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.dto.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersListDTO;
import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.service.IUserService;
import com.jbianchini.meli.socialmeli.service.handler.Initializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;
    Initializer initializer;

    public UserController(IUserService userService, Initializer initializer) {
        this.userService = userService;
        this.initializer = initializer;
    }

    @GetMapping("/createAll")
    public void createAll() {
        this.initializer.createAll();

    }

    @PutMapping("/create")
    public ResponseDTO create(@RequestBody UserDTO userDTO) {
        return this.userService.createUser(userDTO);
    }


    //-------------------

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseDTO follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return this.userService.follow(userId, userIdToFollow);
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<FollowersCountDTO> followersCount(@PathVariable Integer userId) {

        return new ResponseEntity<>(this.userService.getFollowersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<FollowersListDTO> followers(@PathVariable Integer UserID, @RequestParam(defaultValue="") String order) {
        return new ResponseEntity<>(this.userService.getFollowers(UserID, order), HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<FollowedListDTO> followed(@PathVariable Integer UserID,
                                                    @RequestParam(defaultValue="") String order) {
        return new ResponseEntity<>(this.userService.getFollowed(UserID, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseDTO unFollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return this.userService.unFollow(userId, userIdToUnfollow);
    }

}
