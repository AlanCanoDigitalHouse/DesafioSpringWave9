package com.socialMeli.controller;

import com.socialMeli.SocialMeliApplication;
import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.dto.response.ProductsSellersThatUserFollowsDTO;
import com.socialMeli.exception.exception.DateNotValidException;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);
    private final PostService postService;

    public ProductsController(PostService postService) {
        this.postService = postService;
    }

    /**
     * TODO: 0005 Create a new post
     *
     * @param postInfo info of the post, see PostInfoToCreateDTO to see all the required attributes
     * @return Http status, 200 if was been added, 400 if a error occurred
     * @throws ModelAlreadyExists id the post with that id, already exists
     * @throws ParseException     if the date have a unexpected value
     * @throws ModelNotExists     if the id of the user not exists
     */
    @SuppressWarnings("SpellCheckingInspection")
    @PostMapping("/newpost")
    public ResponseEntity<Void> newPost(@Valid @RequestBody PostInfoToCreateDTO postInfo) throws ModelAlreadyExists, ParseException, ModelNotExists, DateNotValidException {
        postService.addNewPost(postInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: 0006 Get list of the posts made by the users that a user follow, that posts must have been created less than a week ago
     *
     * @param userId user that want know the products of the sellers that he follow
     * @return The list of products required
     * @throws ModelNotExists if the user not exists
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ProductsSellersThatUserFollowsDTO> listPostsSellerThatAUserFollow(@PathVariable int userId) throws ModelNotExists {
        return new ResponseEntity<>(postService.postSellersThatUserFollows(userId), HttpStatus.OK);
    }
}
