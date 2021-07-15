package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.response.UserFollowedListResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowersListResponseDTO;
import com.mercadolibre.socialmeli.models.User;
import com.mercadolibre.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userService.init();
    }

    @PostMapping("/users/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    //DEVELOPED AND WORKING

    /**
     * Point 1: Makes a user follow a vendor and sets the Status to OK if everything was correct
     *
     * @param userId
     * @param userIdToFollow
     * @throws AplicationException
     */
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public void follow(HttpServletResponse response, @PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        this.userService.follow(userId, userIdToFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Point 2: Returns a JSON with the number of followers of a user
     *
     * @param userId
     * @return UserFollowersCountResponseDTO And Status OK
     * @throws AplicationException
     */
    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<UserFollowersCountResponseDTO> followersCount(@PathVariable Integer userId) {
        UserFollowersCountResponseDTO responseDTO = this.userService.followersCount(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Point 3: Returns a JSON with a list of followers of a user
     *
     * @param userId
     * @param order  = name_asc(default)
     * @param order  = name_desc
     * @return UserFollowersListResponseDTO And Status OK
     * @throws AplicationException
     */
    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<UserFollowersListResponseDTO> followersList(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order) {
        UserFollowersListResponseDTO responseDTO = this.userService.followersList(userId, order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Point 4: Returns a JSON with a list of followed of a user
     *
     * @param userId
     * @param order  = name_asc(default)
     * @param order  = name_desc
     * @return UserFollowedListResponseDTO And Status OK
     * @throws AplicationException
     */
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserFollowedListResponseDTO> followedList(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order) {
        UserFollowedListResponseDTO responseDTO = this.userService.followedList(userId, order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Point 7: It makes a user stop following a seller and sets the Status to OK if everything was correct
     *
     * @param userId
     * @param userIdToUnFollow
     * @throws AplicationException
     */
    @PostMapping("/users/{userId}/unFollow/{userIdToUnFollow}")
    public void unFollow(HttpServletResponse response, @PathVariable Integer userId, @PathVariable Integer userIdToUnFollow) {
        this.userService.unFollow(userId, userIdToUnFollow);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
