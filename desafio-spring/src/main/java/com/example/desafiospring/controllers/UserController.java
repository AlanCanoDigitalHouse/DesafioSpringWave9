package com.example.desafiospring.controllers;

import com.example.desafiospring.dto.response.SellerResponseDTO;
import com.example.desafiospring.dto.response.UserResponseDTO;
import com.example.desafiospring.exceptions.SellerException;
import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * @param userId
     * @param userIdToFollow
     * @throws SellerException
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void followUser(
            @Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Long userId,
            @Valid @PathVariable @Min(value = 1, message = "User Id to follow has to be a number greater than 0") Long userIdToFollow) throws SellerException {
        this.userService.followSeller(userId, userIdToFollow);
    }

    /**
     * @param sellerId
     * @return ResponseEntity<SellerResponseDTO>
     * @throws SellerException
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerResponseDTO> getNumberOfFollowers(
            @Valid @PathVariable("userId") @Min(value = 1, message = "Seller Id has to be a number greater than 0") Long sellerId) throws SellerException {
        SellerResponseDTO sellerResponseDTO = this.userService.getNumberOfFollowers(sellerId);
        return new ResponseEntity<>(sellerResponseDTO, HttpStatus.OK);
    }

    /**
     * @param sellerId
     * @param order
     * @return ResponseEntity<SellerResponseDTO>
     * @throws SellerException
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerResponseDTO> getFollowers(
            @Valid @PathVariable("userId") @Min(value = 1, message = "Seller Id has to be a number greater than 0") Long sellerId,
            @RequestParam(defaultValue = "id") @Nullable String order) throws SellerException {
        SellerResponseDTO sellerResponseDTO = this.userService.getFollowers(sellerId, order);
        return new ResponseEntity<>(sellerResponseDTO, HttpStatus.OK);
    }

    /**
     * @param userId
     * @param order
     * @return ResponseEntity<UserResponseDTO>
     * @throws SellerException
     * @throws UserException
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserResponseDTO> getFollowed(@Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Long userId, @RequestParam(defaultValue = "id") @Nullable String order) throws SellerException, UserException {
        UserResponseDTO userResponseDTO = this.userService.getFollowed(userId, order);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    /**
     * @param userId
     * @param userIdToUnfollow
     * @throws SellerException
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollowUser(
            @Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Long userId,
            @Valid @PathVariable @Min(value = 1, message = "User Id to unfollow has to be a number greater than 0") Long userIdToUnfollow) throws SellerException {
        this.userService.unfollowSeller(userId, userIdToUnfollow);
    }
}
