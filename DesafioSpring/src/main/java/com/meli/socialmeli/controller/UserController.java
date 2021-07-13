package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.UserFollowersListDTO;
import com.meli.socialmeli.dto.UsersFollowedByListDTO;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

  @Autowired
  UserService service;

  @PostMapping("{userId}/follow/{userIdToFollow}")
  public ResponseEntity<String> addFollower(@PathVariable(name = "userId") Integer followerId,
                                            @PathVariable(name = "userIdToFollow") Integer followedId) {
    service.addFollower(followerId, followedId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
  public ResponseEntity<String> removeFollower(@PathVariable(name = "userId") Integer followerId,
                                               @PathVariable(name = "userIdToUnfollow") Integer followedId) {
    service.removeFollower(followerId, followedId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("{userId}/followers/count")
  public ResponseEntity<Map<String, Object>> getFollowersCount(@PathVariable Integer userId) {
    Integer followersCount = service.getFollowersCount(userId);
    User user = service.findUser(userId);
    HashMap<String, Object> responseMap = new HashMap<>();
    responseMap.put("userId", user.getUserId());
    responseMap.put("userName", user.getUserName());
    responseMap.put("followers_count", followersCount);
    return new ResponseEntity<>(responseMap, HttpStatus.OK);
  }

  @GetMapping("{userId}/followers/list")
  public ResponseEntity<UserFollowersListDTO> getFollowersList(@PathVariable Integer userId) {
    User user = service.findUser(userId);
    UserFollowersListDTO userFollowersListDTO = new UserFollowersListDTO(userId, user.getUserName(), user.getFolllowers());
    return new ResponseEntity<>(userFollowersListDTO, HttpStatus.OK);
  }

  @GetMapping("{userId}/followed/list")
  public ResponseEntity<UsersFollowedByListDTO> findUsersFollowedBy(@PathVariable Integer userId) {
    List<User> usersFollowedBy = service.findUsersFollowedBy(userId);
    User user = service.findUser(userId);
    return new ResponseEntity<>(new UsersFollowedByListDTO(userId, user.getUserName(), usersFollowedBy), HttpStatus.OK);
  }


}
