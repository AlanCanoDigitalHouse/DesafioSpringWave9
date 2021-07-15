package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.users.VendorFollowQtyRespDTO;
import com.mercadolibre.socialmeli.dtos.users.UserFollowsRespDTO;
import com.mercadolibre.socialmeli.exceptions.users.SameUserTwiceException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyFollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyUnfollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;
import com.mercadolibre.socialmeli.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final IUserService iUserService;

    public UserController(@Qualifier("userServ") IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followVendor(@Valid @NotNull @Positive @PathVariable Integer userId,
                                               @Valid @NotNull @Positive @PathVariable Integer userIdToFollow)
            throws UserAlreadyFollowException, SameUserTwiceException, UserNotFoundException, UserAlreadyUnfollowException {
        return new ResponseEntity<>(this.iUserService.followVendor(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/followers/count/")
    public ResponseEntity<VendorFollowQtyRespDTO> countVendorFollows(@Valid @NotNull @Min(0) @PathVariable Integer userId)
            throws UserNotFoundException {
        return new ResponseEntity<>(this.iUserService.countVendorFollows(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/{UserID}/followers/list")
    public ResponseEntity<UserFollowsRespDTO> allVendorFollowers(@Valid @NotNull @Min(0) @PathVariable Integer UserID,
                                                                 @RequestParam(name = "order", required = false, defaultValue = "name_desc") String order)
            throws UserNotFoundException {
        return new ResponseEntity<>(this.iUserService.allVendorFollowers(UserID,order), HttpStatus.OK);
    }

    @GetMapping(path = "/{UserID}/followed/list")
    public ResponseEntity<UserFollowsRespDTO> allVendorFollowed(@Valid @NotNull @Min(0) @PathVariable Integer UserID,
                                                                @RequestParam(name = "order", required = false, defaultValue = "name_desc") String order) throws UserNotFoundException {
        return new ResponseEntity<>(this.iUserService.allUserFollowed(UserID,order), HttpStatus.OK);
    }

    @PostMapping(path = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unfollowVendor(@Valid @NotNull @Positive @PathVariable Integer userId,
                                               @Valid @NotNull @Positive @PathVariable Integer userIdToUnfollow)
            throws UserAlreadyUnfollowException, SameUserTwiceException, UserNotFoundException, UserAlreadyFollowException {
        return new ResponseEntity<>(this.iUserService.unfollowVendor(userId, userIdToUnfollow), HttpStatus.OK);
    }

}
