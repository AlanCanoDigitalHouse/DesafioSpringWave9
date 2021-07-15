package com.kjcelis.social_meli.controllers;

import com.kjcelis.social_meli.dto.PostDTO;
import com.kjcelis.social_meli.dto.response.BuyerListPostResDTO;
import com.kjcelis.social_meli.dto.response.SellerListPpostResDTO;
import com.kjcelis.social_meli.service.SocialMeliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final SocialMeliService socialMeliService;

    public ProductController(SocialMeliService socialMeliService) {
        this.socialMeliService = socialMeliService;
    }

    @PostMapping("/newPost")
    public String newPost(@Valid @RequestBody PostDTO postDTO) {
        return socialMeliService.saveNewPost(postDTO);
    }


    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<BuyerListPostResDTO> listFollowedB(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return new ResponseEntity<>(socialMeliService.getPostListB(userId, order), HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public String newPromoPost(@Valid @RequestBody PostDTO pPostDTO) {
        return socialMeliService.saveNewPromoPost(pPostDTO);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<SellerListPpostResDTO> countPromoSeller(@PathVariable Integer userId) {
        return new ResponseEntity<>(socialMeliService.getCountPromoSeller(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<SellerListPpostResDTO> listProdSeller(@PathVariable Integer userId) {
        return new ResponseEntity<>(socialMeliService.getListProdSeller(userId), HttpStatus.OK);
    }
}


