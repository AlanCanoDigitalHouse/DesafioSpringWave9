package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.respons.UserFollowedListResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersListResponse;
import com.mercadolibre.socialmeli.exceptions.ExpectationFailed;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;
import com.mercadolibre.socialmeli.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    UserServices service;

    public UsersController(UserServices service) {
        this.service = service;
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity(service.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/{userid}/follow/{userIdToFollow}")
    public ResponseEntity postFollow(@PathVariable int userid, @PathVariable int userIdToFollow) throws UserBadRequest, ExpectationFailed {
        return new ResponseEntity(service.postFollow(userid, userIdToFollow), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userid}/followers/count/")
    public ResponseEntity<UserFollowersResponse> getCountFollowers(@PathVariable int userid) throws UserBadRequest {
        return new ResponseEntity(service.getCountFollowers(userid), HttpStatus.OK);
    }

    @GetMapping("/{userid}/followers/list")
    public ResponseEntity<UserFollowersListResponse> getAllFollowers(@PathVariable int userid, @RequestParam(required = false) String order) throws UserBadRequest {
        return new ResponseEntity(service.getAllFollowers(userid, order), HttpStatus.OK);
    }

    @GetMapping("/{userid}/followed/list")
    public ResponseEntity<UserFollowedListResponse> getAllFollowed(@PathVariable int userid, @RequestParam(required = false) String order) throws UserBadRequest {
        return new ResponseEntity(service.getAllFollowed(userid, order), HttpStatus.OK);
    }

    @PostMapping("/{userid}/unfollow/{userIdToFollow}")
    public ResponseEntity postUnfollow(@PathVariable int userid, @PathVariable int userIdToFollow) throws UserBadRequest {
        return new ResponseEntity(service.postUnfollow(userid, userIdToFollow), HttpStatus.NO_CONTENT);
    }


}
