package com.socialMeli.controller;

import com.socialMeli.SocialMeliApplication;
import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.dto.request.product.PromoPostInfoToCreateDTO;
import com.socialMeli.dto.response.CountPromoPostsResponseDTO;
import com.socialMeli.dto.response.ProductsSellersThatUserFollowsDTO;
import com.socialMeli.dto.response.PromoPostsOfAUserResponseDTO;
import com.socialMeli.exception.exception.DateNotValidException;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.exception.exception.OrderNotValidException;
import com.socialMeli.service.IPostService;
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
    private final IPostService postService;

    public ProductsController(IPostService postService) {
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
     * TODO: 0009 Order by date
     *
     * @param userId user that want know the products of the sellers that he follow
     * @return The list of products required
     * @throws ModelNotExists if the user not exists
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ProductsSellersThatUserFollowsDTO> listPostsSellerThatAUserFollow(@PathVariable int userId, @RequestParam(defaultValue = "date_asc") String order) throws ModelNotExists, OrderNotValidException {
        return new ResponseEntity<>(postService.postSellersThatUserFollows(userId, order), HttpStatus.OK);
    }

    /**
     * TODO 0010: Create a new post with a promo
     *
     * @param postInfo info of the post
     * @return Status code 200 if all Ok or 400 if a error was generated
     * @throws ModelAlreadyExists    if that post already exists
     * @throws ParseException        Parse Date exception (technically is exception of simpleDateFormatter)
     * @throws ModelNotExists        If the user id in the payload not exists
     * @throws DateNotValidException If the date have a logic error, a month 15 for example
     */
    @PostMapping("/newpromopost")
    public ResponseEntity<Void> newPromoPost(@Valid @RequestBody PromoPostInfoToCreateDTO postInfo) throws ModelAlreadyExists, ParseException, ModelNotExists, DateNotValidException {
        postService.addNewPost(postInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: 0011  Count of promo post by userId
     *
     * @param userId id of the user to get counter
     * @return counter of promo posts
     * @throws ModelNotExists If the user not exists
     */
    @GetMapping("/{userId}/countPromo/")
    public ResponseEntity<CountPromoPostsResponseDTO> counterPromoPost(@PathVariable int userId) throws ModelNotExists {
        return new ResponseEntity<>(postService.countPromoPosts(userId), HttpStatus.OK);
    }

    /**
     * TODO: 0012 Get list of the promo post of a user
     *
     * @param userId user want know the list of posts
     * @return Name and id of the user with the list of posts
     * @throws ModelNotExists if the user not exists
     */
    @GetMapping("/{userId}/list/")
    public ResponseEntity<PromoPostsOfAUserResponseDTO> getListPromoPosts(@PathVariable int userId) throws ModelNotExists {
        return new ResponseEntity<>(postService.getPromoPostsLists(userId), HttpStatus.OK);
    }
}
