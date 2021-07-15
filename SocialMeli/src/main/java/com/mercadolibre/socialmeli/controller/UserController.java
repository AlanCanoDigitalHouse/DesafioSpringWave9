package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.response.UserWithFollowedDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersCountDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersDTO;
import com.mercadolibre.socialmeli.exception.InvalidRequestParamException;
import com.mercadolibre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Validated
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     *
     * @param userId
     * @param userIdToFollow
     * @return
     */
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    public String follow(
            @PathVariable int userId, @PathVariable int userIdToFollow
    ){
        userService.followUser(userId, userIdToFollow);
        return "user successfully followed";
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}/followers/count/")
    @ResponseStatus(HttpStatus.OK)
    public UserWithFollowersCountDTO getUserWithFollowersCount(
            @PathVariable int userId
    ){
        return userService.getUserWithFollowersCountDTO(userId);
    }

    /**
     *
     * @param UserID
     * @param order
     * @return
     */
    @GetMapping("/users/{UserID}/followers/list")
    @ResponseStatus(HttpStatus.OK)
    public UserWithFollowersDTO getUserWithFollowersDTO(
            @PathVariable int UserID,
            @RequestParam Optional<String> order
            ){
        List<String> validOrderParameters =  Arrays.asList("name_asc", "name_desc");
        if (!validOrderParameters.contains(order.orElse("name_asc"))) throw new InvalidRequestParamException(order.get(), validOrderParameters);
        return userService.getUserWithFollowers(UserID, order.orElse("name_asc"));
    }

    /**
     *
     * @param UserID
     * @return
     */
    @GetMapping("/users/{UserID}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    public UserWithFollowedDTO getUserWithFollowed(
            @PathVariable int UserID
    ){
        return userService.getUSerWithFollowed(UserID);
    }

    /**
     *
     * @param userId
     * @param userIdToUnfollow
     * @return
     */
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(HttpStatus.OK)
    public String unFollow(
            @PathVariable int userId, @PathVariable int userIdToUnfollow
    ){
        userService.unFollowUser(userId, userIdToUnfollow);
        return "user successfully unfollowed";
    }
}
