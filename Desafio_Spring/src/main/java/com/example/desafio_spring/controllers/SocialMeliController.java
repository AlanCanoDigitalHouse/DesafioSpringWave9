package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.FollowSameUserException;
import com.example.desafio_spring.exceptions.InvalidInputVariableException;
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
    public void follow(@PathVariable(value = "userId") Integer id,
                                         @PathVariable(value = "userIdToFollow") Integer idToFollow, HttpServletResponse response) throws UserAlreadyFollowedException, UserNotExistException, FollowSameUserException {
        iSocialMeliService.Follow(id,idToFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    //unfollow
    @PostMapping("/{userId}/unfollow/{userIdToUnFollow}")
    public void unfollow(@PathVariable(value = "userId") Integer id,
                       @PathVariable(value = "userIdToUnFollow") Integer idToUnFollow, HttpServletResponse response) throws UserAlreadyFollowedException, UserNotExistException, FollowSameUserException {
        iSocialMeliService.unFollow(id,idToUnFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<User> followersCount(@PathVariable(value = "userId") Integer userId) throws UserNotExistException {
        return ResponseEntity.ok(iSocialMeliService.FollwerCount(userId));
    }
    @GetMapping("{userId}/followers/list")
    public ResponseEntity<User> followersList(@PathVariable(value = "userId") Integer userId, @RequestParam(required = false, defaultValue = "name_desc") String order) throws UserNotExistException, InvalidInputVariableException {
        return ResponseEntity.ok(iSocialMeliService.FollowersListSorted(order, userId));
    }
    @GetMapping("{userId}/followed/list")
    public ResponseEntity<User> followedList(@PathVariable(value = "userId") Integer userId, @RequestParam(required = false, defaultValue = "name_desc") String order) throws UserNotExistException, InvalidInputVariableException {
        return ResponseEntity.ok(iSocialMeliService.FollwedListSorted(order, userId));
    }
}
