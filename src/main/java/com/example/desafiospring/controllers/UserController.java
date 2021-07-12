package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.response.FollowedListDto;
import com.example.desafiospring.dtos.response.FollowersCountDto;
import com.example.desafiospring.dtos.response.FollowersListDto;
import com.example.desafiospring.dtos.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping(path = "/users")
@Validated
public class UserController {
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@Valid @NotNull @Size(min = 1) @PathVariable String userId){
        return null;
    }
    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followSeller(@PathVariable String userId,@PathVariable String userIdToFollow){
        return null;
    }
    @GetMapping(path = "/{userId}/followers/count")
    public ResponseEntity<FollowersCountDto> contSellerFollowers(@PathVariable String userId){
        return null;
    }
    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<FollowersListDto>getSellerFollowers(@PathVariable String userId){
        return null;
    }
    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<FollowedListDto>getUserFollowedList(@PathVariable String userId){
        return null;
    }
}
