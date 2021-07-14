package com.mercadolibre.desafio.controllers;

import com.mercadolibre.desafio.dtos.responses.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.responses.ResponseFollowed;
import com.mercadolibre.desafio.dtos.responses.ResponseFollowers;
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

    /**
     * Endpoint to follow a user given two user id's
     *
     * @param userId         the user id that do the follow
     * @param userIdToFollow the user id of the user to follow
     * @return a response entity
     * @throws UserException
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@NotBlank @PathVariable Integer userId, @NotBlank @PathVariable Integer userIdToFollow) throws UserException {
        userServices.followUser(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to get the count of the users that follow a user
     *
     * @param userId the user id
     * @return a response entity
     * @throws UserException
     */
    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<ResponseCountFollowers> countUserFollowers(@NotBlank @PathVariable Integer userId) throws UserException {
        return new ResponseEntity<>(userServices.countFollowers(userId), HttpStatus.OK);
    }

    /**
     * Endpoint to get the followers of a user (order by name_asc or name_desc)
     *
     * @param UserID the user id
     * @param order  the user id
     * @return a response entity
     * @throws UserException
     */
    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<ResponseFollowers> getFollowers(@NotBlank @PathVariable Integer UserID, @RequestParam(required = false) String order) throws UserException {
        return new ResponseEntity<>(userServices.getFollowers(UserID, order), HttpStatus.OK);
    }

    /**
     * Endpoint to get the users that follow a user
     *
     * @param UserID the user id
     * @param order  he user id
     * @return a response entity
     * @throws UserException
     */
    @GetMapping("{UserID}/followed/list")
    public ResponseEntity<ResponseFollowed> getFollowed(@NotBlank @PathVariable Integer UserID, @RequestParam(required = false) String order) throws UserException {
        return new ResponseEntity<>(userServices.getFollowed(UserID, order), HttpStatus.OK);
    }

    /**
     * Endpoint to do the unfollow given two users id's
     *
     * @param userId           the user id that do the unfollow
     * @param userIdToUnfollow the user of the user to unfollow
     * @return a response entity
     * @throws UserException
     */
    @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@NotBlank @PathVariable Integer userId, @NotBlank @PathVariable Integer userIdToUnfollow) throws UserException {
        userServices.unfollow(userId, userIdToUnfollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
