package com.meli.joescaos.socialmeli.socialmeli.controllers;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.*;
import com.meli.joescaos.socialmeli.socialmeli.services.BuyerService;
import com.meli.joescaos.socialmeli.socialmeli.services.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jocanas
 */
@RestController
// Entry endpoint for users services
@RequestMapping("/users")
public class UsersController {

    /*
     * Controller attributes
     */
    private BuyerService buyerService;
    private SellerService sellerService;

    /**
     *
     * @param buyerService
     * @param sellerService
     */
    public UsersController(BuyerService buyerService, SellerService sellerService) {
        this.buyerService = buyerService;
        this.sellerService = sellerService;
    }

    /**
     *
     * @param userId
     * @param userIdToFollow
     * @return Response Entity with Http Status
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followSeller(@PathVariable int userId, @PathVariable int userIdToFollow) {
        buyerService.followSeller(userId, userIdToFollow);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     *
     * @param userId
     * @return Response Entity with a SellerFollowersCountDto response and Http Status
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerFollowersCountDto> getSellerFollowers(@PathVariable int userId) {
        return new ResponseEntity(sellerService.getFollowersCount(userId), HttpStatus.OK);
    }

    /**
     *
     * @param userId
     * @param order
     * @return Response Entity with a BuyerFollowingsDto response and Http Status
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerFollowersDto> getSellerFollowersList(@PathVariable int userId,
                                                                     @RequestParam(required = false) String order) {
        return new ResponseEntity<>(sellerService.getFollowersList(userId, order), HttpStatus.OK);
    }

    /**
     *
     * @param userId
     * @param order
     * @return Response Entity with a SellerFollowersDto response and Http Status
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerFollowingsDto> getUserFollowedList(@PathVariable int userId,
                                                                  @RequestParam(required = false) String order) {
        return new ResponseEntity<>(buyerService.getUserFollowedList(userId, order), HttpStatus.OK);
    }

    /**
     *
     * @param userId
     * @param userIdToUnfollow
     * @return Response Entity with Http Status
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowSeller(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        buyerService.unfollowSeller(userId, userIdToUnfollow);
        return new ResponseEntity(HttpStatus.OK);
    }

}
