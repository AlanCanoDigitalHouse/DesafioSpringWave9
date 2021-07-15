package bootcamp.wave9.SocialMeli.controller;

import bootcamp.wave9.SocialMeli.dto.UserDTO;
import bootcamp.wave9.SocialMeli.entity.User;
import bootcamp.wave9.SocialMeli.exception.UserNotFoundException;
import bootcamp.wave9.SocialMeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(UserDTO user) {

        this.userService.createUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        return new ResponseEntity<>(this.userService.getUserList(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) throws UserNotFoundException{
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUserById(@PathVariable int userId) throws UserNotFoundException {
        return new ResponseEntity<>(this.userService.deleteUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> follow(@PathVariable int userId, @PathVariable int userIdToFollow) throws UserNotFoundException {

        this.userService.follow(userId, userIdToFollow);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/followers/count/")
    public Map<String,Object> followersAmount(@PathVariable int userId) throws UserNotFoundException{

        return this.userService.getFollowersAmount(userId);
    }

    @GetMapping("/{UserID}/followers/list")
    public Map<String,Object> followersList(@PathVariable("UserID") int userId, @RequestParam(required = false, defaultValue = "") String order) throws UserNotFoundException{

        return this.userService.getFollowersList(userId, order);
    }

    @GetMapping("/{UserID}/followed/list")
    public Map<String, Object> followedList(@PathVariable("UserID") int userId, @RequestParam(required = false, defaultValue = "") String order) throws UserNotFoundException {

        return this.userService.getFollowedList(userId, order);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow) throws UserNotFoundException {

        this.userService.unfollow(userId, userIdToUnfollow);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
