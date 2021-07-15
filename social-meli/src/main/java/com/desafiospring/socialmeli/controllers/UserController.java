package com.desafiospring.socialmeli.controllers;

import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.services.IUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUser userService;

    public UserController(IUser userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public String followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws UserException {
        this.userService.addFollower(userId, userIdToFollow);
        return "Seguidor agregado correctamente";
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerFollowersCountDTO> getFollowersCount(@PathVariable Integer userId) throws UserException {
        SellerFollowersCountDTO sellerFollowersCountDTO = userService.getFollowersCount(userId);
        return new ResponseEntity<>(sellerFollowersCountDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerFollowersDTO> getFollower(@PathVariable Integer userId ,
                                                          @RequestParam(defaultValue = "") String order) throws UserException {
        SellerFollowersDTO sellerFollowersDTO = userService.getFollowers(userId, order);
        return new ResponseEntity<>(sellerFollowersDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerFollowedDTO> getFollowed(@PathVariable Integer userId,
                                                        @RequestParam(defaultValue = "") String order) throws UserException {
        BuyerFollowedDTO buyerFollowed = userService.getFollowed(userId, order);
        return new ResponseEntity<>(buyerFollowed, HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public String unFollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws UserException {
        this.userService.removeFollower(userId, userIdToUnfollow);
        return "Ya no sigues al vendedor";
    }

}
