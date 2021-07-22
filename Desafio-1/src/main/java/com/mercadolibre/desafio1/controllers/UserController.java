package com.mercadolibre.desafio1.controllers;

import com.mercadolibre.desafio1.dto.response.UserFollowersCountDTO;
import com.mercadolibre.desafio1.dto.response.UserFollowListDTO;
import com.mercadolibre.desafio1.exceptions.UserFollowException;
import com.mercadolibre.desafio1.exceptions.UserNotExistException;
import com.mercadolibre.desafio1.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService socialMeliService) {
        this.userService = socialMeliService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@Valid @PathVariable @NotNull @Positive(message = "Debe ingresar un ID mayor a 0.") Integer userId,
                                        @PathVariable @NotNull @Positive(message = "Debe ingresar un ID mayor a 0.") Integer userIdToFollow) throws UserNotExistException, UserFollowException {
        userService.followUser(userId,userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserFollowersCountDTO> getFollowersCount(@Valid @PathVariable @Positive(message = "Debe ingresar un ID mayor a 0.") Integer userId) throws UserNotExistException {
        return new ResponseEntity<>(userService.countFollowUser(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowListDTO> getFollowersList(@Valid @PathVariable @Positive(message = "Debe ingresar un ID mayor a 0.") Integer userId,
                                                                     @RequestParam(name = "order", required = false, defaultValue = "name_asc") String order) throws UserNotExistException {
        return new ResponseEntity<>(userService.listFollowersUsers(userId,order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowListDTO> getFollowedList(@Valid @PathVariable @Positive(message = "Debe ingresar un ID mayor a 0.") Integer userId,
                                                                    @RequestParam(name = "order", required = false, defaultValue = "name_asc") String order) throws UserNotExistException {
        return new ResponseEntity<>(userService.listFollowedUsers(userId,order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity unfollow(@Valid @NotNull @Positive(message = "Debe ingresar un ID mayor a 0.") @PathVariable Integer userId,
                                          @NotNull @Positive(message = "Debe ingresar un ID mayor a 0.") @PathVariable Integer userIdToFollow) throws UserNotExistException, UserFollowException {
        userService.unfollowUser(userId,userIdToFollow);
        return ResponseEntity.ok().build();
    }
}
