package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseRequestDto;
import com.example.socialmeli.exceptions.InvalidOrder;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.services.ISocialMeliUserServices;
import com.example.socialmeli.services.SocialMeliUserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class SocialMeliUserController {

    ISocialMeliUserServices socialMeliUserServices;

    public SocialMeliUserController(SocialMeliUserServices s){
        this.socialMeliUserServices = s;
    }

    /**
     * Makes a user follow another
     * returns OK if everything went fine.
     * @param userId userIdToFollow
     * @return ResponseRequestDto
     * @throws UserNotFound
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<ResponseRequestDto> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.follow(userId,userIdToFollow),HttpStatus.OK);
    }

    /**
     * Makes a user to stop following another
     * returns OK if everything went fine.
     * @param userId userIdToUnfollow
     * @return ResponseRequestDto
     * @throws UserNotFound
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<ResponseRequestDto> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.unfollow(userId,userIdToUnfollow),HttpStatus.OK);
    }


    /**
     * Returns the amount of followers a user has
     * @param userId
     * @return ResponseCantFollowersDto
     * @throws UserNotFound
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<ResponseCantFollowersDto> followersCount(@PathVariable Integer userId) throws UserNotFound {
        return new ResponseEntity<>(socialMeliUserServices.getFollowersCount(userId), HttpStatus.OK);
    }

    /**
     * Returns the name and userId of the followers of the user
     * @param userId
     * @return ResponseFollowersDto
     * @throws UserNotFound InvalidOrder
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<ResponseFollowersDto> followersInfo(@PathVariable Integer userId,
                                                              @RequestParam(defaultValue="none") String order) throws UserNotFound, InvalidOrder {
        return new ResponseEntity<>(socialMeliUserServices.getFollowersInfo(userId,order), HttpStatus.OK);
    }

    /**
     * Returns the name and userId of the followed of the user
     * @param userId
     * @return ResponseFollowedDto
     * @throws UserNotFound InvalidOrder
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<ResponseFollowedDto> followedInfo(@PathVariable Integer userId,
                                                            @RequestParam(defaultValue = "none") String order) throws UserNotFound, InvalidOrder {
        return new ResponseEntity<>(socialMeliUserServices.getFollowedInfo(userId,order), HttpStatus.OK);
    }

}
