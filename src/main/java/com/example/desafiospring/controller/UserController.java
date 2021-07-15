package com.example.desafiospring.controller;


import com.example.desafiospring.dtos.request.UserRequestDTO;
import com.example.desafiospring.dtos.response.UserResponseDTO;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserIdEqualUserToFollowException;
import com.example.desafiospring.exceptions.UserNotExistsException;
import com.example.desafiospring.exceptions.UserNotFollowedException;
import com.example.desafiospring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;


    @GetMapping("/health")
    public ResponseEntity<List<UserRequestDTO>> getUsers() {
        return new ResponseEntity<List<UserRequestDTO>>(userService.getUsers(), HttpStatus.OK);
    }


    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> follow(@Valid @PathVariable int userId, @PathVariable int userIdToFollow) throws UserIdEqualUserToFollowException, UserNotExistsException, AlreadyFollowedException, UserNotFollowedException {
        userService.follow(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserResponseDTO> countFollowers(@Valid @PathVariable int userId) throws UserNotExistsException {
        return new ResponseEntity<UserResponseDTO>(userService.getUserWithFollowersQuantity(userId), HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<UserRequestDTO> getFollowers(@Valid @RequestParam(defaultValue = "none", required = false) String order, @PathVariable int UserID) throws UserNotExistsException {
        return new ResponseEntity<>(userService.getUserFollowers(UserID, order), HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<UserRequestDTO> getFolloweds(@Valid @RequestParam(defaultValue = "none", required = false) String order, @PathVariable int UserID) throws UserNotExistsException {
        return new ResponseEntity<UserRequestDTO>(userService.getUserFollowed(UserID, order), HttpStatus.OK);
    }


    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollow(@Valid @PathVariable int userId, @PathVariable int userIdToUnfollow) throws UserIdEqualUserToFollowException, UserNotExistsException, UserNotFollowedException, AlreadyFollowedException {
        userService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();

    }


}
