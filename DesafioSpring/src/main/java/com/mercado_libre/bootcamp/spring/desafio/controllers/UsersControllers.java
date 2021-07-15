package com.mercado_libre.bootcamp.spring.desafio.controllers;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowedListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowersCountResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowersListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.services.follow.FollowServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.seller.SellerServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UsersControllers {

    private final SellerServiceImpl followerService;

    private final UserServiceImpl userService;

    private final FollowServiceImpl followService;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> followSeller(@PathVariable int userId, @PathVariable int userIdToFollow) {
        return new ResponseEntity<>(followService.followSeller(userId, userIdToFollow));
    }

    @PostMapping("/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> unfollowSeller(@PathVariable int userId, @PathVariable int userIdToFollow) {
        return new ResponseEntity<>(followService.unfollowSeller(userId, userIdToFollow));
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> getFollowersCount(@PathVariable int userId) {
        return new ResponseEntity<>(followerService.getFollowersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowersListResponseDTO> getFollowersList(@PathVariable int userId,
                                                                     @RequestParam(required = false, defaultValue = "NONE") String order) {
        return new ResponseEntity<>(followerService.getFollowersList(userId, order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowedListResponseDTO> getFollowedList(@PathVariable int userId,
                                                                   @RequestParam(required = false, defaultValue = "NONE") String order) {
        return new ResponseEntity<>(userService.getFollowedList(userId, order), HttpStatus.OK);
    }
}
