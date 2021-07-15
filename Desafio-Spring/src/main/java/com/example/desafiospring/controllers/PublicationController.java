package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.dtos.response.FollowerPostListDTO;
import com.example.desafiospring.exceptions.UserNotFindOrEqualException;
import com.example.desafiospring.services.PublicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class PublicationController {

    private final PublicationServiceImpl publicationService;

    public PublicationController(PublicationServiceImpl publicationService) {
        this.publicationService = publicationService;
    }


    //005
    @PostMapping(path = "/newpost")
    public ResponseEntity<?> newPost(@Valid @RequestBody PublicationRequestDTO publicationRequestDTO) throws UserNotFindOrEqualException {

        return publicationService.newPost(publicationRequestDTO.getUserId(), publicationRequestDTO);
    }

    //006
    @GetMapping(path = "/followed/{userId}/list")
    public FollowerPostListDTO getFollowersPostList(@PathVariable Integer userId,
                                                    @RequestParam(value = "order", defaultValue = "default") String order) throws UserNotFindOrEqualException {
        return publicationService.getAllPost(userId, order);
    }
}
