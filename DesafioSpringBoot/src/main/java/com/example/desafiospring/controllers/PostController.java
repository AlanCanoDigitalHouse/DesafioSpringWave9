package com.example.desafiospring.controllers;



import com.example.desafiospring.dtos.request.PostPromoRequestDto;
import com.example.desafiospring.dtos.request.PostRequestDto;
import com.example.desafiospring.dtos.response.CountPromoDto;
import com.example.desafiospring.dtos.response.FollowedPostDto;
import com.example.desafiospring.dtos.response.PostResponseDto;
import com.example.desafiospring.services.ProductServices;
import com.example.desafiospring.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products")
public class PostController {
    private ProductServices productServices;
    private UserServices userServices;

    /**
     * TODO
     * validate the data input with a regular expression
     * validate userdId in PostController
     */
    public PostController(ProductServices productServices, UserServices userServices) {
        this.productServices = productServices;
        this.userServices = userServices;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<PostResponseDto> getPostFromUser(@PathVariable Integer userId){
        return new ResponseEntity<>(productServices.getPost(userId), HttpStatus.OK);
    }
    @PostMapping(path = "/newpost")
    public ResponseEntity<HttpStatus> addNewPost(@Valid @RequestBody PostRequestDto postRequestDto){
      userServices.getSellerById(postRequestDto.getUserId());
      productServices.createNewPost(postRequestDto);
      return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/newpromopost")
    public ResponseEntity<HttpStatus> addPromoPost(@Valid @RequestBody PostPromoRequestDto postPromo){
        userServices.getSellerById(postPromo.getUserId());
        productServices.createNewPromoPost(postPromo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/followed/{userId}/list")
    public ResponseEntity<FollowedPostDto> getPostFromFollowed(@PathVariable Integer userId,
                                                               @RequestParam(required = false) String order){
        var user = userServices.getClientById(userId);
        var listOfFollowed =  user.getFollowed();
        var list = productServices.sorterWrapper(productServices.getPosts(listOfFollowed),order);
        return new ResponseEntity<>(new FollowedPostDto(userId,list),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/countPromo")
    public ResponseEntity<CountPromoDto> countPromo(@PathVariable Integer userId){
        var promoPost =  productServices.getPromoPost(userId);
        var user = userServices.getUserById(userId);
        return new ResponseEntity<>(new CountPromoDto(user.getId(),user.getUserName(),promoPost.size()),HttpStatus.OK);

    }

    @GetMapping(path = "/{userId}/list")
    public ResponseEntity<FollowedPostDto> getPromoPost(@PathVariable Integer userId){
        var promoPosts = new FollowedPostDto(userId,productServices.getPromoPost(userId));
        return new ResponseEntity<>(promoPosts,HttpStatus.OK);
    }

}
