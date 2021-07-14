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
     * US 0001 POST /users/{userId}/follow/{userIdToFollow}
     * Follows an user
     *
     * @param userId         user Id that will perform the follow action
     * @param userIdToFollow user Id (seller) that will be followed by the user with the previous specified id
     * @throws SellerException exception in cases of: user already follows the seller, user doesn't exist, seller doesnt exist
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void followUser(
            @Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Integer userId,
            @Valid @PathVariable @Min(value = 1, message = "User Id to follow has to be a number greater than 0") Integer userIdToFollow) throws SellerException, UserException {
        this.userService.followSeller(userId, userIdToFollow);
    }

    /**
     * US 0002 GET users/{userId}/followers/count
     * Returns a JSON response with the seller data and the number of followers
     * If the seller hasn't any followers, it will return the object with value 0 for the followers_count field
     *
     * @param sellerId The seller id of what we want to retrieve the data
     * @return ResponseEntity<SellerResponseDTO> Seller data in response
     * @throws SellerException exception in case that seller doesn't exist
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerResponseDTO> getNumberOfFollowers(
            @Valid @PathVariable("userId") @Min(value = 1, message = "Seller Id has to be a number greater than 0") Integer sellerId) throws SellerException {
        SellerResponseDTO sellerResponseDTO = this.userService.getNumberOfFollowers(sellerId);
        return new ResponseEntity<>(sellerResponseDTO, HttpStatus.OK);
    }

    /**
     * US 0003 GET users/{userId}/followers/list
     * Returns a list with the followers of a seller for the specified id
     * If the seller doesn't have any follower, the field "followers" will be an empty array in the response
     *
     * @param sellerId Seller id of what we want to retrieve his followers
     * @param order    Order parameter, it can be name_asc or name_desc, for default it will return the followers ordered by id
     * @return ResponseEntity<SellerResponseDTO>
     * @throws SellerException exception in case that seller doesn't exist
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerResponseDTO> getFollowers(
            @Valid @PathVariable("userId") @Min(value = 1, message = "Seller Id has to be a number greater than 0") Integer sellerId,
            @RequestParam(defaultValue = "id") @Nullable String order) throws SellerException {
        SellerResponseDTO sellerResponseDTO = this.userService.getFollowers(sellerId, order);
        return new ResponseEntity<>(sellerResponseDTO, HttpStatus.OK);
    }

    /**
     * US 0004 GET users/{userId}/followed/list
     * Returns a list with the sellers that an specific user follows
     *
     * @param userId Id of the user
     * @param order  Order of results, it can be name_asc, name_desc or id for default
     * @return ResponseEntity<UserResponseDTO> User data with the sellers that follows
     * @throws UserException Exception in case that user not exists
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserResponseDTO> getFollowed(@Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Integer userId, @RequestParam(defaultValue = "id") @Nullable String order) throws UserException {
        UserResponseDTO userResponseDTO = this.userService.getFollowed(userId, order);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    /**
     * US 0007 POST users/{userId}/unfollow/{userIdToUnfollow}
     * User which userId is in path variable userId unfollows the seller for the seller id specified in userIdToUnfollow path variable
     *
     * @param userId           userId that will perform the unfollow action
     * @param userIdToUnfollow seller that will be unfollowed
     * @throws SellerException Exception in case that userId specified doesn't follow the seller
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollowUser(
            @Valid @PathVariable @Min(value = 1, message = "User Id has to be a number greater than 0") Integer userId,
            @Valid @PathVariable @Min(value = 1, message = "User Id to unfollow has to be a number greater than 0") Integer userIdToUnfollow) throws SellerException {
        this.userService.unfollowSeller(userId, userIdToUnfollow);
    }
}
