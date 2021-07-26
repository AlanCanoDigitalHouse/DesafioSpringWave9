package com.example.demo.Controllers;

import com.example.demo.DTOs.Response.SuccesResponse;
import com.example.demo.Entities.User;
import com.example.demo.Handler.UserSorter;
import com.example.demo.Services.UserService;
import com.sun.source.tree.TryTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService){this.userService=userService;}


    ///Parte 1
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<SuccesResponse> followUser (@PathVariable Integer userId,@PathVariable Integer userIdToFollow){

        if (userService.followAuser(userId, userIdToFollow))
            return new ResponseEntity<>(new SuccesResponse("Usuario seguido"), HttpStatus.OK);
        else
        {
            return new ResponseEntity<>(new SuccesResponse("Usuario no valido"),HttpStatus.BAD_REQUEST);
        }
    }


    ///Parte 2

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<SuccesResponse> getFollowersCount(@PathVariable Integer userId){
        return new ResponseEntity<>(new SuccesResponse("Usuario cuenta con " +userService.followerSize(userId)), HttpStatus.OK);
    }

    ///Parte 3
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SuccesResponse>  getFollowers (@PathVariable Integer userId, @RequestParam(required = false) String order){
        List<User> result = userService.followersOf(userId);
        UserSorter sorter = new UserSorter();
        if (order!=null) {
            if (order.compareTo("name_asc") == 0 || order.compareTo("name_desc") == 0 ) {
                result= sorter.userSorterByName(result,order);
            }
        }
        return new ResponseEntity<>(new SuccesResponse(result.toString()), HttpStatus.OK);
    }

    ///parte 4
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<SuccesResponse> getFollows (@PathVariable Integer userId, @RequestParam(required = false) String order){
        List<User> result = userService.followedByUserByID(userId);
        UserSorter sorter = new UserSorter();
        if (order!=null) {
            if (order.compareTo("name_asc") == 0 || order.compareTo("name_desc") == 0 ) {
                result= sorter.userSorterByName(result,order);
            }
        }
        return new ResponseEntity<>(new SuccesResponse(result.toString()), HttpStatus.OK);

    }

    ///Parte 7
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<SuccesResponse> unFollowUser (@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        if (userService.unfollowAuser(userId,userIdToUnfollow)){
            return new ResponseEntity<>(new SuccesResponse("Se ha dejado de seguir"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new SuccesResponse("Comando erroneo"), HttpStatus.BAD_REQUEST);
    }
 }
