package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.ResponsePostsListDto;
import com.example.socialmeli.dtos.responses.ResponseRequestDto;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.services.ISocialMeliProductServices;
import com.example.socialmeli.services.ISocialMeliUserServices;
import com.example.socialmeli.services.SocialMeliProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class SocialMeliProductController {

    ISocialMeliProductServices socialMeliProductServices;

    public SocialMeliProductController(SocialMeliProductServices s){
        this.socialMeliProductServices = s;
    }


    @PostMapping("/newpost")
    public ResponseEntity<ResponseRequestDto> post(@Valid @RequestBody RequestPostDto requestPostDto) throws UserNotFound {
        return new ResponseEntity<>(socialMeliProductServices.post(requestPostDto),HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ResponsePostsListDto> postsInfo(@PathVariable Integer userId,
                                                          @RequestParam(defaultValue = "none") String order) throws UserNotFound {
        return new ResponseEntity<>(socialMeliProductServices.getPostsInfo(userId,order), HttpStatus.OK);
    }
}
