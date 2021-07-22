package challenge1springboot.socialmeli.controllers;

import challenge1springboot.socialmeli.DTO.response.UserCountFollowersResponseDTO;
import challenge1springboot.socialmeli.DTO.response.UserListFollowedResponseDTO;
import challenge1springboot.socialmeli.DTO.response.UserListFollowersResponseDTO;
import challenge1springboot.socialmeli.entities.User;
import challenge1springboot.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> newFollow(@PathVariable String userId, @PathVariable String userIdToFollow) {
        return userService.newUserFollow(userId, userIdToFollow);
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<UserCountFollowersResponseDTO> countUserFollowers(@PathVariable String userId) {
        return userService.countUserFollowers(userId);
    }

    @GetMapping("/{userID}/followers/list")
    public ResponseEntity<UserListFollowersResponseDTO> listUserFollowers(@PathVariable String userID, @RequestParam(defaultValue = "not_sorted") String order) {
        return userService.listFollowersUser(userID, order);
    }

    @GetMapping("/{userID}/followed/list")
    public ResponseEntity<UserListFollowedResponseDTO> listFollowedByUser(@PathVariable String userID, @RequestParam(defaultValue = "not_sorted") String order) {
        return userService.listFollowedByUser(userID, order);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<HttpStatus> unfollowUser(@PathVariable String userId, @PathVariable String userIdToUnfollow) {
        return userService.unfollowUser(userId, userIdToUnfollow);
    }

    @PostMapping("/new/{userName}")
    public ResponseEntity<User> newFollow(@PathVariable String userName) {
        return new ResponseEntity<>(userService.newUser(userName), HttpStatus.OK);
    }
}
