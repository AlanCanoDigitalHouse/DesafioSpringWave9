package com.socialMeli.controller;

import com.socialMeli.dto.response.CountFollowersResponseDTO;
import com.socialMeli.dto.response.FollowResultResponseDTO;
import com.socialMeli.dto.response.UserFollowedResponseDTO;
import com.socialMeli.dto.response.UserFollowersResponseDTO;
import com.socialMeli.exception.exception.*;
import com.socialMeli.service.IUserService;
import com.socialMeli.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
public class UsersController {
    final IUserService userService;

    public UsersController(IUserService userService) {
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
    public ResponseEntity<FollowResultResponseDTO> follow(@PathVariable int userId, @PathVariable int userIdToFollow) throws ModelNotExists, FollowHimselfException, AlreadyFollowedException {
        return new ResponseEntity<>(userService.follow(userId, userIdToFollow), HttpStatus.OK);
    }

    /**
     * TODO: 0002 A user can get the count of users that following him
     *
     * @param userId id of the user interested
     * @return The id, name of the user and the counter of how many followers have
     * @throws ModelNotExists If the user id not exists
     */
    @SuppressWarnings("unused")
    @GetMapping("{userId}/followers/count/")
    public ResponseEntity<CountFollowersResponseDTO> countFollowers(@PathVariable int userId) throws ModelNotExists {
        return new ResponseEntity<>(userService.getCountOfFollowers(userId), HttpStatus.OK);
    }

    /**
     * TODO: 0003 Get the list of the users that follow a other user
     * TODO: 0008 Users can be ordered by name
     *
     * @param userID user that want know the followers lists
     * @return Name and id of the user, with their list of users with the id and name
     * @throws ModelNotExists The id provided not exists
     */
    @GetMapping("{userID}/followers/list")
    public ResponseEntity<UserFollowersResponseDTO> listFollowers(@PathVariable int userID, @RequestParam(defaultValue = "name_asc") String order) throws ModelNotExists, OrderNotValidException {
        return new ResponseEntity<>(userService.getListFollowers(userID, order), HttpStatus.OK);
    }

    /**
     * TODO: 0004 Get the list of the users that a user follow
     * TODO: 0008 Users can be ordered by name
     *
     * @param userID user that want know the followers lists
     * @return Name and id of the user, with their list of users with the id and name
     * @throws ModelNotExists The id provided not exists
     */
    @GetMapping("{userID}/followed/list")
    public ResponseEntity<UserFollowedResponseDTO> listFollowed(@PathVariable String userID, @RequestParam(defaultValue = "name_asc") String order) throws ModelNotExists, OrderNotValidException {
        return new ResponseEntity<>(userService.getListUsersFollowed(Integer.parseInt(userID), order), HttpStatus.OK);
    }

    /**
     * TODO: 0007 User can unfollow a user
     *
     * @param userID user that want know the followers lists
     * @return Name and id of the user, with their list of users with the id and name
     * @throws ModelNotExists The id provided not exists
     */
    @PostMapping("{userID}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollowUser(@PathVariable int userID, @PathVariable int userIdToUnfollow) throws UserDontFollowThisUser, ModelNotExists {
        userService.unfollowUser(userID, userIdToUnfollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
