package com.example.desafiospring.controllers;

import com.example.desafiospring.dto.response.SellerResponseDTO;
import com.example.desafiospring.dto.response.UserResponseDTO;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void followUser(@PathVariable @NotBlank Long userId, @PathVariable @NotBlank Long userIdToFollow) throws SellerException {
        this.userService.followSeller(userId, userIdToFollow);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerResponseDTO> getNumberOfFollowers(@PathVariable("userId") @NotBlank Long sellerId) throws SellerException {
        SellerResponseDTO sellerResponseDTO = this.userService.getNumberOfFollowers(sellerId);
        return new ResponseEntity<>(sellerResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerResponseDTO> getFollowers(@PathVariable("userId") @NotBlank Long sellerId, @RequestParam(defaultValue = "id") @Nullable String order) throws SellerException {
        SellerResponseDTO sellerResponseDTO = this.userService.getFollowers(sellerId, order);
        return new ResponseEntity<>(sellerResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserResponseDTO> getFollowed(@PathVariable @NotBlank Long userId, @RequestParam(defaultValue = "id") @Nullable String order) throws SellerException, UserException {
        UserResponseDTO userResponseDTO = this.userService.getFollowed(userId, order);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollowUser(@PathVariable @NotBlank Long userId, @PathVariable @NotBlank Long userIdToUnfollow) throws SellerException {
        this.userService.unfollowSeller(userId, userIdToUnfollow);
    }
}
