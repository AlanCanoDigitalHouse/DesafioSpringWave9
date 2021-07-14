package com.socialmeli.controllers;

import com.socialmeli.dtos.responses.CountFollowersResponseDTO;
import com.socialmeli.dtos.responses.FollowedResponseDTO;
import com.socialmeli.dtos.responses.FollowersResponseDTO;
import com.socialmeli.exceptions.ApiException;
import com.socialmeli.services.SocialMeliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/users")
public class SocialMeliUserController {

    SocialMeliService socialMeliService;

    public SocialMeliUserController(SocialMeliService socialMeliService){
        this.socialMeliService = socialMeliService;
    }

    /**
     * Seguir un usuario.
     * @param userId id del usuario seguidor.
     * @param userIdToFollow id del usuario a seguir.
     * @throws ApiException, ConstraintViolationException
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId,
                       @PathVariable @Pattern(regexp = "[0-9]+", message = "userIdToFollow must be a number") String userIdToFollow) {

        socialMeliService.follow(Integer.valueOf(userId), Integer.valueOf(userIdToFollow));
    }

    /**
     * Obtener la cantidad de seguidores de un usuario.
     * @param userId id del usuario.
     * @return cantidad de seguidores.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<CountFollowersResponseDTO> countFollowers(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId){

        return new ResponseEntity<>(socialMeliService.countFollowers(Integer.valueOf(userId)), HttpStatus.OK);
    }

    /**
     * Obtener la lista de seguidores de un usuario.
     * @param userId id del usuario.
     * @return lista de seguidores.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<FollowersResponseDTO> followers(@PathVariable("UserID") @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId){

        return new ResponseEntity<>(socialMeliService.getFollowers(Integer.valueOf(userId)), HttpStatus.OK);
    }

    /**
     * Obtener la lista de seguidos de un usuario.
     * @param userId id del usuario.
     * @return lista de seguidos.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<FollowedResponseDTO> followed(@PathVariable("UserID") @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId){

        return new ResponseEntity<>(socialMeliService.getFollowed(Integer.valueOf(userId)), HttpStatus.OK);
    }

    /**
     * Obtener la lista de seguidores de un usuario.
     * @param userId id del usuario.
     * @param order indica el orden de los valores a devolver pude ser name_asc|name_desc
     * @return lista de seguidores.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping(value = "/{UserID}/followers/list", params = {"order"})
    public ResponseEntity<FollowersResponseDTO> orderFollowers(@PathVariable("UserID") @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId,
                                                               @RequestParam @Pattern(regexp = "name_asc|name_desc", message = "order must be name_asc or name_desc") String order){

        return new ResponseEntity<>(socialMeliService.getFollowers(Integer.valueOf(userId), order), HttpStatus.OK);
    }

    /**
     * Obtener la lista de seguidos de un usuario.
     * @param userId id del usuario.
     * @param order indica el orden de los valores a devolver pude ser name_asc|name_desc
     * @return lista de seguidos.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping(value = "/{UserID}/followed/list", params = {"order"})
    public ResponseEntity<FollowedResponseDTO> orderFollowed(@PathVariable("UserID") @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId,
                                                             @RequestParam @Pattern(regexp = "name_asc|name_desc", message = "order must be name_asc or name_desc") String order){

        return new ResponseEntity<>(socialMeliService.getFollowed(Integer.valueOf(userId), order), HttpStatus.OK);
    }

    /**
     * Dejar de seguir un usuario.
     * @param userId id del usuario seguidor.
     * @param userIdToUnfollow id del usuario a dejar de seguir.
     * @throws ApiException, ConstraintViolationException
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollow(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId,
                         @PathVariable @Pattern(regexp = "[0-9]+", message = "userIdToUnfollow must be a number") String userIdToUnfollow) {

        socialMeliService.unfollow(Integer.valueOf(userId), Integer.valueOf(userIdToUnfollow));
    }

}
