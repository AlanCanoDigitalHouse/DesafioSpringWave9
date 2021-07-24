package com.socialmeli.controllers;

import com.socialmeli.dtos.ResponseDTO;
import com.socialmeli.dtos.request.SortEnum;
import com.socialmeli.services.IUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersSocialController {

    private final IUsersService usersService;

    /**
     * Constructor with dependency injections service users
     * @param usersService service users
     */
    public UsersSocialController(IUsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Calls the service that user follow a seller from the ID.
     * @param userId id of the user who wants to follow the seller
     * @param userIdToFollow seller id to follow
     * @return OK status if everything was correct
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<ResponseDTO> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(usersService.followUser(userId, userIdToFollow), HttpStatus.OK);
    }

    /**
     * Calls the service that user unfollow a seller from the ID.
     * @param userId id of the user who wants to unfollow the seller
     * @param userIdToUnFollow seller id to unfollow
     * @return OK status if everything was correct
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnFollow}")
    public ResponseEntity<ResponseDTO> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnFollow) {
        return new ResponseEntity<>(usersService.unfollowUser(userId, userIdToUnFollow), HttpStatus.OK);
    }

    /**
     * Calls the service that counts the number of followers a certain user has based on their ID.
     * @param userId user id to count followers
     * @return number of followers
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<ResponseDTO> countFollowers(@PathVariable Integer userId) {
        return new ResponseEntity<>(usersService.countFollowers(userId), HttpStatus.OK);
    }

    /**
     * Calls the service that lists the number of followers a given user has based on his or her ID.
     * @param userId user id to list followers
     * @param order ordering of users by name
     * @return basic information about followers list of users
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<ResponseDTO> listFollowers(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc", required = false) SortEnum order) {
        return new ResponseEntity<>(usersService.listFollowers(userId, order), HttpStatus.OK);
    }

    /**
     * Calls the service that lists the followed users of a given user based on the user ID
     * @param userId user id to list followed
     * @param order ordering of users by name
     * @return basic information about followed list of users
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<ResponseDTO> listFollowed(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc", required = false) SortEnum order) {
        return new ResponseEntity<>(usersService.listFollowed(userId, order), HttpStatus.OK);
    }

}
