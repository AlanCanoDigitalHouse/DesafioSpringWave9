package com.example.demo.controller;

import com.example.demo.dtos.response.CountFollowersResponseDto;
import com.example.demo.dtos.response.ListFollowedResponseDto;
import com.example.demo.dtos.response.ListFollowersResponseDto;
import com.example.demo.dtos.response.ResponseOkDto;
import com.example.demo.exceptions.userExceptions.Exceptions.UserNotFoundException;
import com.example.demo.services.interfaces.BuyerService;
import com.example.demo.services.interfaces.SellerService;
import com.example.demo.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    BuyerService buyerService;
    SellerService sellerService;
    UserService userService;

    public UserController(BuyerService buyerService , SellerService sellerService , UserService userService){
        this.buyerService = buyerService;
        this.sellerService = sellerService;
        this.userService = userService;
    }

    @PostMapping("/create/{userId}/{userName}")
    public ResponseEntity<ResponseOkDto> createPromoPost(@PathVariable int userId, @PathVariable String userName) {
        return new ResponseEntity<>(this.userService.addUser(userId , userName) , HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{userToFollow}")
    public ResponseEntity<ResponseOkDto> followSeller(@PathVariable int userId, @PathVariable int userToFollow) {
        try {
            buyerService.followSeller(userService.getUser(userId), userService.getUser(userToFollow));
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseOkDto(HttpStatus.BAD_REQUEST.value(),"User not found") , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseOkDto(HttpStatus.OK.value(),"User followed succesfuly") , HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userToUnfollow}")
    public ResponseEntity<Void> unfollowSeller(@PathVariable int userId,@PathVariable int userToUnfollow) {
            buyerService.unfollowSeller(userService.getUser(userId), userService.getUser(userToUnfollow));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersResponseDto> countFollowers(@PathVariable int userId) {
        return new ResponseEntity<>(sellerService.countFollowers(userService.getUser(userId)), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<ListFollowersResponseDto> whoFollows(@PathVariable int userId, @RequestParam(value = "order", required = false) String order) {
        return new ResponseEntity<>(sellerService.listFollowers(userService.getUser(userId) , userService.getUsers(), order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<ListFollowedResponseDto> whoIsFollowing(@PathVariable int userId, @RequestParam(value = "order", required = false) String order){
        return new ResponseEntity<>(buyerService.listFollowed(userService.getUser(userId) , userService.getUsers(), order), HttpStatus.OK);
    }

/*
{
    "userId":3,
    "id_post":23,
    "date":"10-07-2021",
    "detail":{
        "product_id":1,
        "productName":"silla gammer",
        "type":"Gamer",
        "brand":"Racer",
        "color":"Red",
        "notes":"Special"
    },
    "category":100,
    "price":1500
}
 */
}