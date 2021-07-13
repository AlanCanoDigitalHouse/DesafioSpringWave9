package com.example.desafiospring.controllers;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerListResponseDTO;
import com.example.desafiospring.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRESTController {
    UserService userService;

    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<FollowUserResponseDTO> createLink (@Valid FollowUserRequestDTO followUserRequestDTO){
        return new ResponseEntity<>(userService.followUser(followUserRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<FollowerCountResponseDTO> followerCount (@Valid OnlyUserIDRequestDTO onlyUserIDRequestDTO){
        return new ResponseEntity<>(userService.followerCount(onlyUserIDRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowerListResponseDTO> followerList (@Valid OnlyUserIDRequestDTO onlyUserIDRequestDTO){
        return new ResponseEntity<>(userService.followerList(onlyUserIDRequestDTO), HttpStatus.OK);
    }

}
