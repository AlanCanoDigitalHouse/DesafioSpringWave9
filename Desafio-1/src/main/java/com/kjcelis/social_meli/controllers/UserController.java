package com.kjcelis.social_meli.controllers;

import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.SellerDTO;
import com.kjcelis.social_meli.exceptions.ErrorMessage;
import com.kjcelis.social_meli.exceptions.MeliAppException;
import com.kjcelis.social_meli.service.SocialMeliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private final SocialMeliService socialMeliService;

    public UserController(SocialMeliService socialMeliService) {
        this.socialMeliService = socialMeliService;
    }

    @GetMapping("/initdata")
    public String initInfoD() {
        socialMeliService.initInfo();
        return HttpStatus.OK.toString();
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public String followSeller(@NotNull @PathVariable Integer userId, @NotNull @PathVariable Integer userIdToFollow) {
        return socialMeliService.followSeller(userId, userIdToFollow);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerDTO> countTotal(@PathVariable Integer userId) throws ErrorMessage, MeliAppException {
        return new ResponseEntity<>(socialMeliService.getCountUsersFV(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerDTO> listFollowersS(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return new ResponseEntity<>(socialMeliService.getListFollowS(userId, order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerDTO> listFollowedB(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return new ResponseEntity<>(socialMeliService.getListFollowedB(userId, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public String unfollowS(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return socialMeliService.unfollowSeller(userId, userIdToUnfollow);
    }
}
