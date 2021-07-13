package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.response.FollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersResponseDTO;
import com.mercadolibre.socialmeli.exception.ServiceException;
import com.mercadolibre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> getFollowersCount(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("{userId}/followers/list")
    public ResponseEntity<FollowersResponseDTO> getFollowers(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("{UserID}/followed/list")
    public ResponseEntity<?> getFollowed(@PathVariable Integer UserID) {
        return new ResponseEntity<>(userService.getFollowed(UserID), HttpStatus.OK);
    }

    @PostMapping("{userId}/follow/{userIdToFollow}")
    public HttpStatus follow(@PathVariable int userId, @PathVariable int userIdToFollow) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            userService.addFollower(userId, userIdToFollow);
        } catch (ServiceException serviceException) {
            responseStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return responseStatus;
    }
}
