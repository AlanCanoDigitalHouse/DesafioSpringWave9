package com.socialMeli.controller;

import com.socialMeli.dto.response.CountFollowersResponseDTO;
import com.socialMeli.dto.response.FollowResultResponseDTO;
import com.socialMeli.dto.response.WhoFollowUserResponseDTO;
import com.socialMeli.exception.exception.AlreadyFollowedException;
import com.socialMeli.exception.exception.FollowHimselfException;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    final UserService userService;

    public UsersController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    /**
     * TODO: 0001 A user can follow other user
     *
     * @param userId         id of the user that going to follow
     * @param userIdToFollow id user that been followed
     * @return The name and id of the follower and the name and id of the user followed
     * @throws ModelNotExists           If the user follower or the followed not exists
     * @throws FollowHimselfException   If the client try to follow himself ( ex: user 1 wanna follow user 1, has no sense!)
     * @throws AlreadyFollowedException If the user already follow tat user
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<FollowResultResponseDTO> follow(@PathVariable String userId, @PathVariable String userIdToFollow) throws ModelNotExists, FollowHimselfException, AlreadyFollowedException {
        return new ResponseEntity<>(userService.follow(Integer.parseInt(userId), Integer.parseInt(userIdToFollow)), HttpStatus.OK);
    }

    /**
     * TODO: 0002 A user can get the count of users that following him
     *
     * @param userId id of the user interested
     * @return The id, name of the user and the counter of how many followers have
     * @throws ModelNotExists If the user id not exists
     */
    @GetMapping("{userId}/followers/count/")
    public ResponseEntity<CountFollowersResponseDTO> countFollowers(@PathVariable String userId) throws ModelNotExists {
        return new ResponseEntity<>(userService.getCountOfFollowers(Integer.parseInt(userId)), HttpStatus.OK);
    }

    /**
     * Get the list of the users that follow a other user
     *
     * @param userID user that want know the followers lists
     * @return Name and id of the user, with their list of users with the id and name
     * @throws ModelNotExists The id provided not exists
     */
    @GetMapping("{userID}/followers/list")
    public ResponseEntity<WhoFollowUserResponseDTO> listFollowers(@PathVariable String userID) throws ModelNotExists {
        return new ResponseEntity<>(userService.getListFollowers(Integer.parseInt(userID)), HttpStatus.OK);
    }
}
