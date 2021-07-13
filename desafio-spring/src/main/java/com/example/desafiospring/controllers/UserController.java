package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.exceptions.UserNotFollowedException;
import com.example.desafiospring.services.IFollowerService;
import com.example.desafiospring.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private IFollowerService followerService;
    private IUserService userService;

    public UserController(IFollowerService followerService, IUserService userService) {
        this.followerService = followerService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followUser(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userIdToFollow)
            throws AlreadyFollowedException, UserNotExistException, SameUserException {
        followerService.followUserById(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowUser(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userIdToUnfollow)
            throws UserNotExistException, SameUserException, UserNotFollowedException {
        followerService.unfollowUserById(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<UserFollowersDto> numFollowersByUserId(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId)
            throws UserNotExistException {
        return ResponseEntity.ok(this.followerService.numFollowersByUserId(userId));
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersDto> getUserFollowers(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @RequestParam(required = false, defaultValue = "name_asc") @NotBlank String order)
            throws UserNotExistException {
        return ResponseEntity.ok(this.followerService.getUserFollowers(userId, order));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowersDto> getUserFollowed(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @RequestParam(required = false, defaultValue = "name_asc") @NotBlank String order)
            throws UserNotExistException {
        return ResponseEntity.ok(this.followerService.getUserFollowed(userId, order));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
