package com.apismeli.socialmeli.controllers;

import com.apismeli.socialmeli.dtos.request.MarketDTO;
import com.apismeli.socialmeli.dtos.response.CountFollowersDTO;
import com.apismeli.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //initializer
    @GetMapping("/initializer")
    public ResponseEntity<MarketDTO> initializer() {
        return new ResponseEntity<>(userService.initializer(), HttpStatus.OK);
    }

    //001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity toFollow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws Exception {
        return userService.toFollow(userId, userIdToFollow);
    }

    //002
    @GetMapping("/{userId}/followers/count")
    public CountFollowersDTO countFollowers(@PathVariable Integer userId) throws Exception {
        return userService.countFollowers(userId);
    }

    //003 ,004 y  008
    @GetMapping("/{UserID}/{follow}/list")
    public Object showUsers(@PathVariable Integer UserID, @PathVariable String follow, @RequestParam(value = "order", required = false) String order) throws Exception {
        return userService.showUsers(UserID, follow, order);
    }

    //007
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws Exception {

        return userService.unfollow(userId, userIdToUnfollow);
    }


}
