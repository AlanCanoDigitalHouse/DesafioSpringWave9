package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.request.FollowRequest;
import com.mercadolibre.socialmeli.dto.response.CountFollowersResponse;
import com.mercadolibre.socialmeli.dto.response.UserFollowedResponse;
import com.mercadolibre.socialmeli.dto.response.UserFollowersResponse;
import com.mercadolibre.socialmeli.entity.Follow;
import com.mercadolibre.socialmeli.service.IFollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final IFollowService followService;

    public UserController(IFollowService followService) {
        this.followService = followService;
    }

    //TODO:US_0001
    @PostMapping(value = "/{userId}/follow/{userIdFollow}")
    public ResponseEntity createFollow(@Valid @PathVariable Integer userId, @PathVariable Integer userIdFollow) {
        return ResponseEntity.ok(followService.createFollow(new FollowRequest(userId, userIdFollow)));
    }

    //TODO:US_0002
    @GetMapping(value = "/{userId}/followers/count")
    public ResponseEntity<CountFollowersResponse> countFollowers(@Valid @PathVariable Integer userId) {
        return ResponseEntity.ok(followService.countFollowers(userId));
    }

    //TODO:US_0003
    @GetMapping(value = "/{UserID}/followers/list")
    public ResponseEntity<UserFollowersResponse> listFollowers(@Valid @PathVariable Integer UserID,@RequestParam(required = false) String order) {
        return ResponseEntity.ok(followService.followers(UserID, order));
    }

    //TODO:US_0004
    @GetMapping(value = "/{UserID}/followed/list")
    public ResponseEntity<UserFollowedResponse> listFollowed(@Valid @PathVariable Integer UserID) {
        return ResponseEntity.ok(followService.listUserIdFollowed(UserID));
    }

    //TODO:US_0007
    @PostMapping(value = "/{userId}/unfollow/{userIdTofollow}")
    public ResponseEntity unFollow(@Valid @PathVariable Integer userId, @PathVariable Integer userIdTofollow) {
        return ResponseEntity.ok(followService.findByUserIdAndUserIdToFollow(userId, userIdTofollow));
    }

    //TODO:UserAll
    @PostMapping(value = "/listFollow")
    public ResponseEntity<List<Follow>> userAll() {
        return ResponseEntity.ok(followService.findAll());
    }
}
