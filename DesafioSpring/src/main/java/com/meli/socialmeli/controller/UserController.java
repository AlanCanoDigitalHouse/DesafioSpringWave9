package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.UserFollowersListDTO;
import com.meli.socialmeli.dto.UsersFollowedByListDTO;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.service.SocialMeliService;
import com.meli.socialmeli.util.constants.SocialMeliConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
@Validated
public class UserController {

  @Autowired
  SocialMeliService service;

  @PostMapping("{userId}/follow/{userIdToFollow}")
  public ResponseEntity<String> addFollower(@PathVariable(name = "userId")
                                            @Min(message = SocialMeliConstants.USER_ID_MIN_MESSAGE, value =
                                                    SocialMeliConstants.USER_ID_MIN_VALUE)
                                                    Integer followerId,
                                            @PathVariable(name = "userIdToFollow")
                                            @Min(message = SocialMeliConstants.USER_ID_MIN_MESSAGE, value =
                                                    SocialMeliConstants.USER_ID_MIN_VALUE)
                                                    Integer followedId) {
    service.addFollower(followerId, followedId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
  public ResponseEntity<String> removeFollower(@PathVariable(name = "userId")
                                               @Min(message = SocialMeliConstants.USER_ID_MIN_MESSAGE, value =
                                                       SocialMeliConstants.USER_ID_MIN_VALUE)
                                                       Integer followerId,
                                               @PathVariable(name = "userIdToUnfollow") @Min(message = SocialMeliConstants.USER_ID_MIN_MESSAGE, value =
                                                       SocialMeliConstants.USER_ID_MIN_VALUE)
                                                       Integer followedId) {
    service.removeFollower(followerId, followedId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("{userId}/followers/count")
  public ResponseEntity<Map<String, Object>> getFollowersCount(@PathVariable
                                                               @Min(message = SocialMeliConstants.USER_ID_MIN_MESSAGE, value =
                                                                       SocialMeliConstants.USER_ID_MIN_VALUE)
                                                                       Integer userId) {
    Integer followersCount = service.getFollowersCount(userId);
    User user = service.findUser(userId);
    HashMap<String, Object> responseMap = new HashMap<>();
    responseMap.put("userId", user.getUserId());
    responseMap.put("userName", user.getUserName());
    responseMap.put("followers_count", followersCount);
    return new ResponseEntity<>(responseMap, HttpStatus.OK);
  }

  @GetMapping("{userId}/followers/list")
  public ResponseEntity<UserFollowersListDTO> getFollowersList(@PathVariable
                                                               @Min(message = SocialMeliConstants.USER_ID_MIN_MESSAGE, value =
                                                                       SocialMeliConstants.USER_ID_MIN_VALUE)
                                                                       Integer userId,
                                                               @RequestParam(required = false)
                                                               @Pattern(regexp =
                                                                       SocialMeliConstants.ORDER_NAME_PATTERN,
                                                                       message = SocialMeliConstants.ORDER_NAME_PATTERN_MESSAGE)
                                                                       String order) {
    //todo add patter annotation validation
    User user = service.findUser(userId, order);
    UserFollowersListDTO userFollowersListDTO = new UserFollowersListDTO(userId, user.getUserName(), user.getFolllowers());
    return new ResponseEntity<>(userFollowersListDTO, HttpStatus.OK);
  }

  @GetMapping("{userId}/followed/list")
  public ResponseEntity<UsersFollowedByListDTO> findUsersFollowedBy(@PathVariable
                                                                    @Min(message =
                                                                            SocialMeliConstants.USER_ID_MIN_MESSAGE,
                                                                            value = SocialMeliConstants.USER_ID_MIN_VALUE)
                                                                            Integer userId,
                                                                    @RequestParam(required = false)
                                                                    @Pattern(regexp =
                                                                            SocialMeliConstants.ORDER_NAME_PATTERN,
                                                                            message = SocialMeliConstants.ORDER_NAME_PATTERN_MESSAGE)
                                                                            String order) {
    List<User> usersFollowedBy = service.findUsersFollowedBy(userId, order);
    User user = service.findUser(userId);
    return new ResponseEntity<>(new UsersFollowedByListDTO(userId, user.getUserName(), usersFollowedBy), HttpStatus.OK);
  }

}
