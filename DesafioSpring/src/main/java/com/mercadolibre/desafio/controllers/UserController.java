package com.mercadolibre.desafio.controllers;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.repositories.UserRepository;
import com.mercadolibre.desafio.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@NotBlank @PathVariable Integer userId, @NotBlank @PathVariable Integer userIdToFollow) throws UserException {
        userServices.followUser(userId,userIdToFollow);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<ResponseCountFollowers> countUserFollowers(@NotBlank @PathVariable Integer userId) throws UserException {
        return new ResponseEntity<>(userServices.countFollowers(userId),HttpStatus.OK);
    }


}
