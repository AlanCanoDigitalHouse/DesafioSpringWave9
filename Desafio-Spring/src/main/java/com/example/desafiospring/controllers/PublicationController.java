package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.dtos.response.FollowerPostListDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import com.example.desafiospring.services.PublicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class PublicationController {

    private final PublicationServiceImpl publicationService;

    public PublicationController(PublicationServiceImpl publicationService) {
        this.publicationService = publicationService;
    }


    //005
    @PostMapping(path = "/newpost")
    public ResponseEntity<?> newPost(@RequestBody PublicationRequestDTO publicationRequestDTO) throws UserNotFindException {
        System.out.println("newPost");
        return publicationService.newPost(publicationRequestDTO.getUserId(), publicationRequestDTO);
    }

    //006
    @GetMapping(path = "/followed/{userId}/list")
    public FollowerPostListDTO getFollowersPostList(@PathVariable Integer userId, @RequestParam(value = "order", defaultValue = "default") String order) throws UserNotFindException {
        System.out.println("get follower list post");
        System.out.println("orde: "+ order);
        return publicationService.getAllPost(userId, order);
    }
}
