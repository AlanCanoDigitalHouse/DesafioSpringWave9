package challenge1springboot.socialmeli.controllers;

import challenge1springboot.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> newFollow (@PathVariable String userId, @PathVariable String userIdToFollow){
        return userService.newUserFollow(userId, userIdToFollow);
    }

}
