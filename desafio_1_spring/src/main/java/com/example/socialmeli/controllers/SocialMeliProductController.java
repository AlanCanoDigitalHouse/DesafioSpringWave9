package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.ResponsePostsListDto;
import com.example.socialmeli.dtos.responses.ResponseRequestDto;
import com.example.socialmeli.exceptions.IncompatibleRequest;
import com.example.socialmeli.exceptions.InvalidOrder;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.services.ISocialMeliProductServices;
import com.example.socialmeli.services.SocialMeliProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class SocialMeliProductController {

    ISocialMeliProductServices socialMeliProductServices;

    public SocialMeliProductController(SocialMeliProductServices s){
        this.socialMeliProductServices = s;
    }


    @PostMapping("/newpost")
    /**
     * Saves the product in the DB and the post inside
     * the user who made it, said post points to de product.
     * Returns OK if everithing went fine.
     * @param RequestPostDto
     * @return ResponseRequestDto
     * @throws UserNotFound
     */
    public ResponseEntity<ResponseRequestDto> post(@Valid @RequestBody RequestPostDto requestPostDto) throws UserNotFound, IncompatibleRequest {
        return new ResponseEntity<>(socialMeliProductServices.post(requestPostDto),HttpStatus.OK);
    }

    /**
     * returns a Json with all the posts of the
     * last 15 days of the users that follow
     * the user whose userId was entered.
     * The posts can be viewed at
     * ascending or descending order
     * @param userId y order
     * @return ResponsePostsListDto
     * @throws UserNotFound  InvalidOrder
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ResponsePostsListDto> postsInfo(@PathVariable Integer userId,
                                                          @RequestParam(defaultValue = "none") String order) throws UserNotFound, InvalidOrder {
        return new ResponseEntity<>(socialMeliProductServices.getPostsInfo(userId,order), HttpStatus.OK);
    }
}
