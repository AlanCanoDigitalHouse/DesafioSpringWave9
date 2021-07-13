package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.response.*;
import com.example.desafiospring.services.UserServices;
import com.example.desafiospring.utils.Factory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping(path = "/users")
@Validated
public class UserController {
    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@Valid @NotNull @Min(1) @PathVariable Integer userId) {
        var user = userServices.getUserById(userId);
        return new ResponseEntity<>(new UserResponseDto(user.getId(),user.getUserName()),HttpStatus.OK);
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followSeller(@Valid @NotNull @Min(1) @PathVariable Integer userId,
                                               @Valid @NotNull @Min(1) @PathVariable Integer userIdToFollow) {
        userServices.followSeller(userId,userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/followers/count")
    public ResponseEntity<FollowersCountDto> contSellerFollowers(@Valid @NotNull @Min(1) @PathVariable Integer userId) {
        var seller = userServices.getSellerById(userId);
        var counter = seller.getFollowers().size();
        return new ResponseEntity<>(new FollowersCountDto(seller.getId(),seller.getUserName(),counter)
                ,HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<SellerResponseDto> getSellerFollowers(@Valid @NotNull @Min(1) @PathVariable Integer userId) {
        var seller = userServices.getSellerById(userId);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<ClientResponseDto> getUserFollowedList(@Valid @NotNull @Min(1) @PathVariable Integer userId) {
        var client = userServices.getClientById(userId);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @PostMapping(path = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<HttpStatus> unFollowSeller(@Valid @NotNull @Min(1) @PathVariable Integer userId,
                                                     @Valid @NotNull @Min(1) @PathVariable Integer userIdToUnfollow){
        userServices.unFollowSeller(userId,userIdToUnfollow);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
