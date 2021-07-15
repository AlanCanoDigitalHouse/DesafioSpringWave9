package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.responsedtos.BuyerWithAllFollowsResponseDTO;
import com.example.desafiospring.dtos.responsedtos.SellerTotalFollowersResponseDTO;
import com.example.desafiospring.dtos.responsedtos.SellerWithAllFollowersResponseDTO;
import com.example.desafiospring.exceptions.OrderNotExistsException;
import com.example.desafiospring.exceptions.UserAlreadyFollowToSellerException;
import com.example.desafiospring.exceptions.UserNotFollowSellerException;
import com.example.desafiospring.exceptions.UserNotFoundByIdException;
import com.example.desafiospring.services.IBuyerService;
import com.example.desafiospring.services.ISellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IBuyerService iBuyerService;
    private final ISellerService iSellerService;

    public UserController(IBuyerService iBuyerService, ISellerService iSellerService){
        this.iSellerService = iSellerService;
        this.iBuyerService = iBuyerService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws UserNotFoundByIdException, UserAlreadyFollowToSellerException {
        this.iBuyerService.followSeller(userId, userIdToFollow);
        return new ResponseEntity<>("TODO OK", HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerTotalFollowersResponseDTO> getTotalFollowers(@PathVariable Integer userId) throws UserNotFoundByIdException {
        return new ResponseEntity<>(this.iSellerService.getTotalFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerWithAllFollowersResponseDTO> getFollowersList(@PathVariable Integer userId, @RequestParam(value="order", required = false) String order) throws UserNotFoundByIdException, OrderNotExistsException {
        return new ResponseEntity<>(this.iSellerService.getFollowers(userId, order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerWithAllFollowsResponseDTO> getFollowedList(@PathVariable Integer userId, @RequestParam(value="order", required = false) String order) throws UserNotFoundByIdException, OrderNotExistsException {
        return new ResponseEntity<>(this.iBuyerService.getFollows(userId, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws UserNotFollowSellerException, UserAlreadyFollowToSellerException, UserNotFoundByIdException {
        this.iBuyerService.unFollowSeller(userId,userIdToUnfollow);
        return new ResponseEntity<>("TODO OK", HttpStatus.OK);
    }

}
