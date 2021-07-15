package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.dto.UserFollowersCountDTO;
import com.meli.socialmeli.dto.UserFollowersListDTO;
import com.meli.socialmeli.dto.UsersFollowedByListDTO;
import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.service.user.UserService;
import com.meli.socialmeli.util.constants.SocialMeliConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("users")
@Validated
public class UserController {

  @Autowired
  UserService service;

  @PostMapping("{userId}/follow/{userIdToFollow}")
  public ResponseEntity<String> addFollower(@PathVariable(name = "userId") @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer followerId, @PathVariable(name = "userIdToFollow") @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer followedId) throws UserNotFoundException {
    service.addFollower(followerId, followedId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
  public ResponseEntity<String> removeFollower(@PathVariable(name = "userId") @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer followerId, @PathVariable(name = "userIdToUnfollow") @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer followedId) throws UserNotFoundException {
    service.removeFollower(followerId, followedId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("{userId}/followers/count")
  public ResponseEntity<UserFollowersCountDTO> getFollowersCount(@PathVariable @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer userId) throws UserNotFoundException {
    Integer followersCount = service.getFollowersCount(userId);
    UserDTO user = service.getUser(userId);
    UserFollowersCountDTO userFollowersCountDTO = new UserFollowersCountDTO(userId, user.getUserName(), followersCount);
    return new ResponseEntity<>(userFollowersCountDTO, HttpStatus.OK);
  }

  @GetMapping("{userId}/followers/list")
  public ResponseEntity<UserFollowersListDTO> getFollowersList(@PathVariable @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer userId, @RequestParam(required = false) @Pattern(regexp = SocialMeliConstants.ORDER_NAME_PATTERN, message = SocialMeliConstants.VALIDATION_ORDER_PATTERN_MESSAGE) String order) throws UserNotFoundException {
    List<UserDTO> followers = service.getUserFollowers(userId, order);
    UserDTO user = service.getUser(userId);
    UserFollowersListDTO userFollowersListDTO = new UserFollowersListDTO(userId, user.getUserName(), followers);
    return new ResponseEntity<>(userFollowersListDTO, HttpStatus.OK);
  }

  @GetMapping("{userId}/followed/list")
  public ResponseEntity<UsersFollowedByListDTO> findUsersFollowedBy(@PathVariable @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer userId, @RequestParam(required = false) @Pattern(regexp = SocialMeliConstants.ORDER_NAME_PATTERN, message = SocialMeliConstants.VALIDATION_ORDER_PATTERN_MESSAGE) String order) throws UserNotFoundException {
    List<UserDTO> usersFollowedBy = service.getUsersFollowedBy(userId, order);
    UserDTO user = service.getUser(userId);
    return new ResponseEntity<>(new UsersFollowedByListDTO(userId, user.getUserName(), usersFollowedBy), HttpStatus.OK);
  }

}
