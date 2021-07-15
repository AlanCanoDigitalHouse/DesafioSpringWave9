package com.example.DesafioSpring.controllers;

import com.example.DesafioSpring.dtos.BuyerResponseDTO;
import com.example.DesafioSpring.dtos.BuyerResponseMsgDTO;
import com.example.DesafioSpring.dtos.SellerResponseDTO;
import com.example.DesafioSpring.dtos.SellerResponseSingleDTO;
import com.example.DesafioSpring.servicies.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1
    @PostMapping("{userId}/follow/{userIdFollow}")
    public ResponseEntity<BuyerResponseMsgDTO> follow(@Valid @NotEmpty(message = "UserId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userId,
                                                      @Valid @NotEmpty(message = "SellerId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userIdFollow){
        return new ResponseEntity<>(userService.followSeller(userId, userIdFollow), HttpStatus.OK);
    }

    // 2
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerResponseSingleDTO> followersCount(@Valid @NotEmpty(message = "UserId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userId){
        return new ResponseEntity<>(userService.countFollowersSeller(userId), HttpStatus.OK );
    }

    // 3
//    @GetMapping("/{userId}/followers/list")
//    public ResponseEntity<SellerResponseDTO> followersList(@Valid @PathVariable Integer userId){
//        return new ResponseEntity<>(userService.listFollowersSeller(userId), HttpStatus.OK );
//    }

    // 4
//    @GetMapping("/{userId}/followed/list")
//    public ResponseEntity<BuyerResponseDTO> followedList(@Valid @PathVariable Integer userId){
//        return new ResponseEntity<>(userService.listFollowedSeller(userId), HttpStatus.OK );
//    }

    // 7
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<BuyerResponseMsgDTO> unfollow(@Valid @NotEmpty(message = "UserId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userId,
                                                        @Valid @NotEmpty(message = "UserId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userIdToUnfollow){
        return new ResponseEntity<>(userService.unfollow(userId, userIdToUnfollow), HttpStatus.OK);
    }

    // 8
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerResponseDTO> followersListOrder(@Valid @NotEmpty(message = "UserId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userId,
                                                                @Valid @RequestParam(required = false) String order){
        return new ResponseEntity<>(userService.listFollowersSellerAscDes(userId, order), HttpStatus.OK );
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerResponseDTO> followedListOrder(@Valid @NotEmpty(message = "UserId is required") @Min(message = "Enter an integer greater than zero", value = 0) @NotNull(message = "Null values not allowed") @PathVariable Integer userId,
                                                              @Valid @RequestParam(required = false) String order){
        return new ResponseEntity<>(userService.listFollowedSellerAscDes(userId, order), HttpStatus.OK );
    }

    // Initializer
    @PostMapping("/start-users")
    public ResponseEntity<String> start(){
        return new ResponseEntity<>(userService.start(), HttpStatus.OK );
    }


}
