package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.services.IFollowerService;
import com.example.desafiospring.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    public ResponseEntity<Void> followUser(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId,
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userIdToFollow)
            throws AlreadyFollowedException, UserNotExistException {
        followerService.followUserById(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<UserFollowersDto> numFollowersByUserId(
            @Valid @PathVariable @Min(message = "El id no puede ser negativo", value = 0) Long userId)
            throws UserNotExistException {
        return ResponseEntity.ok(this.followerService.numFollowersByUserId(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
