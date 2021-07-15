package com.example.demo.Controllers;

import com.example.demo.DTOs.FollowListDTO;
import com.example.demo.DTOs.FollowersDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // GET

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersDTO> getFollowersAmount(@PathVariable int userId) throws CustomExceptionHandler {
        return new ResponseEntity(userService.getFollowersAmount(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowListDTO> getListFollowers(@PathVariable int userId, @RequestParam(defaultValue = "name_asc") String order) throws CustomExceptionHandler {
        return new ResponseEntity(userService.getListFollowers(userId, order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowListDTO> getListFollowed(@PathVariable int userId, @RequestParam(defaultValue = "name_asc") String order) throws CustomExceptionHandler {
        return new ResponseEntity(userService.getListFollowing(userId, order), HttpStatus.OK);
    }

    // POST

    @PostMapping("{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> addFollower(@PathVariable int userId, @PathVariable int userIdToFollow) throws CustomExceptionHandler {
        userService.addFollower(userId, userIdToFollow);
        return new ResponseEntity("User with ID #" + userId + " now follows user with ID #" + userIdToFollow, HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> removeFollower(@PathVariable int userId, @PathVariable int userIdToUnfollow) throws CustomExceptionHandler {
        userService.removeFollower(userId, userIdToUnfollow);
        return new ResponseEntity("User with ID #" + userId + " doesn't follow user with ID #" + userIdToUnfollow + " anymore.", HttpStatus.OK);
    }

}