package com.desafio.demo.controllers;

import com.desafio.demo.dtos.users.FollowedResponseDto;
import com.desafio.demo.dtos.users.FollowersCountResponseDto;
import com.desafio.demo.dtos.users.FollowersResponseDto;
import com.desafio.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //pegarle primero a este endpoint para cargar la dummy data
    @PostMapping("/initialize")
    public ResponseEntity<String> initialize() {
        userService.initialize();
        return ResponseEntity.ok("Ready to try");
    }

    // realizar follow a un determinado vendedor
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followUser(@PathVariable @Positive int userId,  @PathVariable @Positive int userIdToFollow) {
        userService.followUser(userId, userIdToFollow);
        return ResponseEntity.ok("user: " + userId + " is following user:"+ userIdToFollow );
    }

    //obtener la cantidad de usuarios que siguen a un determinado vendedor
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDto> countFollowers(@PathVariable int userId) {
        FollowersCountResponseDto followersCountResponseDto = userService.countFollowers(userId);
        return ResponseEntity.ok(followersCountResponseDto);
    }

    //lista todos los usuarios que siguen a un determinado vendedor
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowersResponseDto> getFollowersList(@PathVariable int userId, @RequestParam(value = "order", required = false) String order) {
        FollowersResponseDto followersResponseDto = userService.getFollowersList(userId, order);
        return new ResponseEntity<>(followersResponseDto, HttpStatus.OK);
    }

    //obtener listado de  todos los vendedores a los que sigue un usuario
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowedResponseDto> getFollowedList(@PathVariable int userId, @RequestParam(required = false) String order) {
        FollowedResponseDto followedResponseDto = userService.getFollowedList(userId, order);
        return new ResponseEntity<>(followedResponseDto, HttpStatus.OK);
    }

    //dejar de seguir
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        userService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.ok("user: "+userId+" stop following user: "+ userIdToUnfollow);
    }
}
