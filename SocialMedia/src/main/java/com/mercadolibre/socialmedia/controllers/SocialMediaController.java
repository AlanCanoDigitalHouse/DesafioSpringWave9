package com.mercadolibre.socialmedia.controllers;

import com.mercadolibre.socialmedia.dtos.PostDto;
import com.mercadolibre.socialmedia.dtos.request.PostPromoQuantityResponse;
import com.mercadolibre.socialmedia.dtos.request.PostPromoRequest;
import com.mercadolibre.socialmedia.dtos.request.PostRequestDto;
import com.mercadolibre.socialmedia.dtos.response.FollowedUsersResponse;
import com.mercadolibre.socialmedia.dtos.response.FollowersQuantityResponse;
import com.mercadolibre.socialmedia.dtos.response.FollowersUsersResponse;
import com.mercadolibre.socialmedia.dtos.response.PostsUserResponse;
import com.mercadolibre.socialmedia.services.ISocialMediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("/")
@Validated
public class SocialMediaController {

    ISocialMediaService iSocialMediaService;

    public SocialMediaController(ISocialMediaService iSocialMediaService){
        this.iSocialMediaService = iSocialMediaService;
    }

    // 0001
    @PostMapping("/users/{userId}/follow/{userIdFollow}")
    public ResponseEntity followUser(@Valid @PathVariable @Min(0) Integer userId, @Valid @PathVariable @Min(0) Integer userIdFollow){
        return iSocialMediaService.followUser(userId, userIdFollow);
    }

    // 0002
    @GetMapping("/users/{userId}/followers/count/")
    public FollowersQuantityResponse coutFollowers(@Valid @PathVariable @Min(0) Integer userId) {
        return iSocialMediaService.countUserFollowers(userId);
    }

    // 0003
    @GetMapping("/users/{UserID}/followers/list")
    public FollowersUsersResponse userFollowersByVendor( @Valid @PathVariable @Min(0) Integer UserID, @Valid @RequestParam(defaultValue = "name_asc") @NotBlank String order){
        return iSocialMediaService.getFollowersUserList(UserID, order);
    }

    // 0004
    @GetMapping("/users/{UserID}/followed/list")
    public FollowedUsersResponse usersFollowed(@Valid @Min(0) @PathVariable Integer UserID, @RequestParam(defaultValue = "name_asc") String order){
        return iSocialMediaService.getFollowedUserList(UserID, order);
    }

    // 0005
    @PostMapping("/products/newpost")
    public ResponseEntity addNewPost(@Valid @RequestBody PostRequestDto postDto){
        return iSocialMediaService.addNewPost(postDto);
    }

    // 0006
    @GetMapping("/products/followed/{userId}/list")
    public PostsUserResponse getPosts(@Valid  @Min(0) @PathVariable Integer userId, @RequestParam(defaultValue = "date_asc") String order){
        return iSocialMediaService.getLastPostsByUser(userId, order);
    }

    // 0007
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowUser(@Valid @PathVariable @Min(0) Integer userId, @PathVariable Integer userIdToUnfollow){
        return iSocialMediaService.unfollowUser(userId, userIdToUnfollow);
    }

    // 0010
    @PostMapping("/products/newpromopost")
    public ResponseEntity addNewPostPromo(@Valid @RequestBody PostPromoRequest postPromoRequest){
        return iSocialMediaService.addNewPost(postPromoRequest);
    }

    @GetMapping("/products/{userId}/countPromo/")
    public PostPromoQuantityResponse countPostPromo(@Valid @PathVariable @Min(0) Integer userId){
        return iSocialMediaService.countPosts(userId);
    }
}
