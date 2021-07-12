package com.mercadolibre.desafio.controllers;

import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.repositories.UserRepository;
import com.mercadolibre.desafio.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
