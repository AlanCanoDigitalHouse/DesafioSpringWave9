package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.dto.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersListDTO;
import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    /**
     * Creates a new user
     *
     * @param userDTO DTO containing user name
     * @return ResponseDTO with the response status and the argument data
     */
    @PostMapping("/create")
    public ResponseDTO create(@RequestBody @Valid UserDTO userDTO) {
        return this.userService.createUser(userDTO);
    }

    /**
     * Exercise 1: Make user passed as argument to follow the second user.
     *
     * @param userId         follower user id
     * @param userIdToFollow followed user id
     * @return ResponseDTO with the response status and the argument data
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseDTO follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return this.userService.follow(userId, userIdToFollow);
    }

    /**
     * Exercise 2: Gets the amount of users following the user with the id passed.
     *
     * @param userId followed user id
     * @return ResponseEntity containing {@link FollowersCountDTO} with the count information
     */
    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<FollowersCountDTO> followersCount(@PathVariable Integer userId) {

        return new ResponseEntity<>(this.userService.getFollowersCount(userId), HttpStatus.OK);
    }

    /**
     * Exercise 3: Retrieves the list of followers of the user id passed as argument, in certain order. The default
     * order is ascending.
     *
     * @param UserID followed user id
     * @param order  String specifying order
     * @return ResponseEntity containing {@link FollowersListDTO} with the followers
     */
    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<FollowersListDTO> followers(@PathVariable Integer UserID,
                                                      @RequestParam(defaultValue = "") String order) {
        return new ResponseEntity<>(this.userService.getFollowers(UserID, order), HttpStatus.OK);
    }

    /**
     * Exercise 4: Retrieves the list of followed users of the user id passed as argument, in certain order. The default
     * order is ascending.
     *
     * @param UserID follower user id
     * @param order  String specifying order
     * @return ResponseEntity containing {@link FollowedListDTO} with the followed users
     */
    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<FollowedListDTO> followed(@PathVariable Integer UserID,
                                                    @RequestParam(defaultValue = "") String order) {
        return new ResponseEntity<>(this.userService.getFollowed(UserID, order), HttpStatus.OK);
    }

    /**
     * Exercise 7: Makes a user to unfollow another one.
     *
     * @param userId           follower user id
     * @param userIdToUnfollow followed user id
     * @return ResponseDTO with the response status and the argument data
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseDTO unFollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return this.userService.unFollow(userId, userIdToUnfollow);
    }

}
