package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.UserAlreadyFollowedException;
import com.example.desafio_spring.exceptions.UserNotExistException;
import com.example.desafio_spring.services.interfaces.ISocialMeliService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class SocialMeliController {
    ISocialMeliService iSocialMeliService;

    public SocialMeliController(ISocialMeliService iSocialMeliService) {
        this.iSocialMeliService = iSocialMeliService;
    }
    @PostMapping("/create")
    public ResponseEntity<User> saveUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(iSocialMeliService.saveUser(userRequestDTO));
    }
    @GetMapping("/all")
    public ResponseEntity<Map<Integer, User>> getAllUsers(){
        return ResponseEntity.ok(iSocialMeliService.getAllUsers());
    }

    //Follow user
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void saveUser(@PathVariable(value = "userId") Integer id,
                                         @PathVariable(value = "userIdToFollow") Integer idToFollow, HttpServletResponse response) throws UserAlreadyFollowedException {
        iSocialMeliService.Follow(id,idToFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<User> followersCount(@PathVariable(value = "userId") Integer userId) throws UserNotExistException {
        return ResponseEntity.ok(iSocialMeliService.FollwerCount(userId));
    }
    @GetMapping("{userId}/followers/list")
    public ResponseEntity<User> followersList(@PathVariable(value = "userId") Integer userId) throws UserNotExistException {
        return ResponseEntity.ok(iSocialMeliService.FollowersList(userId));
    }
    @GetMapping("{userId}/followed/list")
    public ResponseEntity<User> followedList(@PathVariable(value = "userId") Integer userId) throws UserNotExistException {
        return ResponseEntity.ok(iSocialMeliService.FollwedList(userId));
    }
}
