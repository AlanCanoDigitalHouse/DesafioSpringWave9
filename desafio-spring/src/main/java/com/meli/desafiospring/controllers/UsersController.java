package com.meli.desafiospring.controllers;

import com.meli.desafiospring.DTOs.response.FollowedListResponseDTO;
import com.meli.desafiospring.DTOs.response.FollowersCountResponseDTO;
import com.meli.desafiospring.DTOs.response.FollowersListResponseDTO;
import com.meli.desafiospring.DTOs.response.SimpleUserDTO;
import com.meli.desafiospring.routers.Router;
import com.meli.desafiospring.services.UserManager;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    UserManager userManager;

    public UsersController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/initialize")
    public ResponseEntity<List<SimpleUserDTO>> initializer() {
        return userManager.initialize();
    }

    @GetMapping("/users")
    public ResponseEntity<List<SimpleUserDTO>> users() {
        return userManager.getUsers();
    }


    // US0001
    @PostMapping(Router.FOLLOW)
    public ResponseEntity<String> follow(@PathVariable @NotNull @Range(min = 0, max = 10000000)
                                         @Digits(integer = 8, fraction = 0) Integer userId,
                                         @PathVariable @NotNull @Range(min = 0, max = 10000000)
                                         @Digits(integer = 8, fraction = 0) Integer userIdToFollow) {
        return userManager.follow(userId, userIdToFollow);
    }

    // US0007
    @PostMapping(Router.UNFOLLOW)
    public ResponseEntity<String> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return userManager.unfollow(userId, userIdToUnfollow);
    }

    // US0002
    @GetMapping(Router.FOLLOWERS_COUNT)
    public ResponseEntity<FollowersCountResponseDTO> followers_count(@PathVariable Integer userId) {
        return new ResponseEntity<>(userManager.followersCount(userId), HttpStatus.OK);
    }

    // US0003
    @GetMapping(Router.FOLLOWERS_LIST)
    public ResponseEntity<FollowersListResponseDTO> followers_list(@PathVariable Integer userId,
                                                                   @RequestParam(required=false) String order) {
        return userManager.followersList(userId, order);
    }

    // US0004
    @GetMapping(Router.FOLLOWED_LIST)
    public ResponseEntity<FollowedListResponseDTO> sellers_followed_list(@PathVariable Integer userId,
                                                                         @RequestParam(required=false) String order) {
        return userManager.followedList(userId, order);
    }

}
