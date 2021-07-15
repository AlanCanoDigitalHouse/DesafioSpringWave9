package com.mercadolibre.desafio.spring.controllers;

import com.mercadolibre.desafio.spring.dtos.response.NumberFollowersResponseDto;
import com.mercadolibre.desafio.spring.dtos.request.PostDto;
import com.mercadolibre.desafio.spring.dtos.response.PostResponseDto;
import com.mercadolibre.desafio.spring.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("apiSocialMeli")
public class AppSocialMeliController {

    private final IService service;

    public AppSocialMeliController(IService service) {
        this.service = service;
    }

    /** US 0001
     *
     * @param userId: ID of the user who wants to follow another (follower user).
     * @param userIdToFollow:ID of the user to follow (followed user).
     * @return Status 200 - OK - operation successful.
     * A follower is added to a user.
     */

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followingUsers(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        service.followUser(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** US 0002
     * @param userId: ID of the user whose number of followers you want to know.
     * @return Status 200 - OK + Number of followers that follow a user.
     * The number of users who follow a certain user in particular is reported.
     */

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<NumberFollowersResponseDto> numberOfFollowers(@PathVariable Integer userId) {
        NumberFollowersResponseDto response = service.getNumberOfUsers(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /** US 0003
     * Parameter UserID: User ID you want to know that users follow
     * Parameter order: (optional) - To list the following users alphabetically ascending or descending.
     * -order: name_asc - ascending alphabetical order.
     * -order: name_desc - descending alphabetical order.
     * Return: Status 200 (OK) + List of followers for a particular user.
     * List users who follow a certain user.
    */
    @GetMapping("/users/{UserID}/followers/list")
    public ResponseEntity<?> showFollowersList(@PathVariable Integer UserID, @RequestParam(required = false, defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(service.showListFollowers(UserID,order), HttpStatus.OK);
    }

    /** US 0004
     * @param UserID: ID of the user whose users are following them
     * @param order: (optional) - To list users followed alphabetically ascending or descending.
     * @return Status 200 - OK + List of followers for a particular user.
     *-order: name_asc - ascending alphabetical order.
     *-order: name_desc - descending alphabetical order.
     * List the users who are followed by a certain user.
     */

    @GetMapping("/users/{UserID}/followed/list")
    public ResponseEntity<?> showFollowedList(@PathVariable Integer UserID, @RequestParam(required = false, defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(service.showListFollowed(UserID, order), HttpStatus.OK);
    }

    /** US 0005
     * @param post: JSON with the data of the new post.
     * @return Status 200 - OK - operation successful.
     * Add a new post
     */
    @PostMapping("/products/newpost")
    public ResponseEntity<?> setNewPost(@RequestBody @Valid PostDto post) {
        service.createNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** US 0006
     * @param userId: ID of the user for which you want to know the posts of the users you follow.
     * @param order: parameter: (optional) - To list the publications of the followed users ordered by date.
     * @return Status 200 (OK) + list of publications of the followed users.
     * -order: date_asc - sort by ascending date.
     * -order: date_desc - order by descending date.
     * List the posts of the last 14 days of all the users followed by a certain user
     */

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<PostResponseDto> postsList(@PathVariable Integer userId, @RequestParam(required = false, defaultValue = "date_asc") String order) {
        return new ResponseEntity<>(service.getPosts(userId, order), HttpStatus.OK);
    }

    /** US 0007
     * @param userId: ID of the user who wants to stop following another (follower user).
     * @param userIdToUnFollow:ID of the user who wants to unfollow (followed user).
     * @return Status 200 - OK - operation successful.
     * A follower is removed from a user.
     */
    @PostMapping("/users/{userId}/unfollow/{userIdToUnFollow}")
    public ResponseEntity<?> unFollowingUsers(@PathVariable Integer userId, @PathVariable Integer userIdToUnFollow) {
        service.unFollowUser(userId, userIdToUnFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /** US 0010
     * @param post: JSON with the data of the new promo post.
     * @return Status 200 - OK - operation successful.
     * Add a new post with promo
     */
    @PostMapping("/products/newpromopost")
    public ResponseEntity<?> setNewPromoPost(@RequestBody @Valid PostDto post) {
        service.createNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** US 0011
     * @param userId
     * @return Status 200 - OK - operation successful.
     * Shows the quantity of products with promo
     */
    @GetMapping("/products/{userId}/countPromo/")
    public ResponseEntity<?> numberOfPromoPosts(@PathVariable Integer userId){
        return new ResponseEntity<>(service.numberOfPromoPostById(userId), HttpStatus.OK);
    }
    /** US 0012
     * @param userId: ID  of the user that shows the product list
     * @param order: parameter: (optional) - To list the products with promo ordered by name.
     *-order: name_asc - ascending alphabetical order.
     *-order: name_desc - descending alphabetical order.
     * @return Status 200 - OK - operation successful.
     * Lists the products with promo made of a user
     */
    @GetMapping("/products/{userId}/list/")
    public ResponseEntity<?> listOfPromoPosts(@PathVariable Integer userId, @RequestParam (required = false, defaultValue = "name_asc")  String order){
        return new ResponseEntity<>(service.listOfPromoPostById(userId, order), HttpStatus.OK);
    }

}
