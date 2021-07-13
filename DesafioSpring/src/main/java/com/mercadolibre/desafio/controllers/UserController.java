package com.mercadolibre.desafio.controllers;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@NotBlank @PathVariable Integer userId, @NotBlank @PathVariable Integer userIdToFollow) throws UserException {
        userServices.followUser(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<ResponseCountFollowers> countUserFollowers(@NotBlank @PathVariable Integer userId) throws UserException {
        return new ResponseEntity<>(userServices.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<ResponseFollowers> getFollowers(@NotBlank @PathVariable Integer UserID, @RequestParam String order) throws UserException {
        return new ResponseEntity<>(userServices.getFollowers(UserID,order), HttpStatus.OK);
    }

    @GetMapping("{UserID}/followed/list")
    public ResponseEntity<ResponseFollowed> getFollowed(@NotBlank @PathVariable Integer UserID,@RequestParam String order) throws UserException {
        return new ResponseEntity<>(userServices.getFollowed(UserID,order), HttpStatus.OK);
    }

    @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@NotBlank @PathVariable Integer userId, @NotBlank @PathVariable Integer userIdToUnfollow) throws UserException {
        userServices.unfollow(userId,userIdToUnfollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
