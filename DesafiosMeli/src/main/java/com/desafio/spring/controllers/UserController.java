package com.desafio.spring.controllers;

import com.desafio.spring.dtos.UserDto;
import com.desafio.spring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /*01*/
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity addFollower(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){

        userService.addFollower(userId, userIdToFollow);
        return new ResponseEntity(HttpStatus.OK);
    }
    //02
    @GetMapping("/{userId}/followers/count")
    public Integer totalFollowers(@PathVariable Integer userId){
        return userService.totalFollowers(userId);
    }

    //03
    @GetMapping("/{userId}/followers/list")
    public List<UserDto> allFollowers(@PathVariable Integer userId , @RequestParam(required = false, defaultValue = "name_asc") String order){
        return userService.allFollowers(userId, order);
    }

    //04
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> allFollowed(@PathVariable Integer  userId , @RequestParam(required = false, defaultValue = "name_asc") String order){
        return new ResponseEntity<>(userService.allFollowed(userId, order),HttpStatus.OK);
    }

    //07
    @PostMapping("/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity deleteFollower(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        userService.deleteFollower(userId, userIdToFollow);
        return new ResponseEntity(HttpStatus.OK);
    }

}

