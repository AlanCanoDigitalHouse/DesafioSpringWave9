package desafiospringw9.demo.controllers;


import desafiospringw9.demo.dtos.FollowListDTO;
import desafiospringw9.demo.dtos.FollowersCountDTO;
import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    IUserService userService;

    //0001
    @PostMapping("{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followUser(@PathVariable int userId, @PathVariable int userIdToFollow)
            throws UserNotValidException, RelationNotValidException{
        userService.addFollower(userId, userIdToFollow);
        return new ResponseEntity("Relation Added", HttpStatus.OK);
    }

    //0002
    @GetMapping("{userId}/followers/count")
    public ResponseEntity<FollowersCountDTO> countFollowers(@PathVariable int userId) throws UserNotValidException{
        return new ResponseEntity(userService.countFollowers(userId), HttpStatus.OK);
    }

    //0003
    @GetMapping("{userId}/followers/list")
    public ResponseEntity<FollowListDTO> getFollowers(@PathVariable int userId) throws UserNotValidException{
        return new ResponseEntity<>(userService.getFollowers(userId), HttpStatus.OK);
    }

}
