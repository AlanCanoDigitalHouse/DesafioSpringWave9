package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.FollowedListDTO;
import com.mercadolibre.socialmeli.dtos.FollowersCountDTO;
import com.mercadolibre.socialmeli.dtos.FollowersListDTO;
import com.mercadolibre.socialmeli.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    private final UserService sellerService;

    public UsersController(UserService sellerService) {
        this.sellerService = sellerService;
    }

    /*
     * US 0001: Poder realizar la acción de “Follow” (seguir) a un determinado vendedor.
     */
    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        sellerService.follow(userId, userIdToFollow);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
     * US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
     */
    @PostMapping(path = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        sellerService.unfollow(userId, userIdToUnfollow);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
     * US 0002: Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor.
     */
    @GetMapping(path = "/{userId}/followers/count")
    public ResponseEntity<FollowersCountDTO> followersCount(@PathVariable Integer userId) {
        return new ResponseEntity<>(sellerService.getFollowersCount(userId), HttpStatus.OK);
    }

    /*
     * US 0003: Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
     * US 0008: Ordenamiento alfabético ascendente y descendente
     */
    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<FollowersListDTO> followersList(@PathVariable  Integer userId,
                                                          @RequestParam(required = false) String order) {
        return new ResponseEntity<>(sellerService.getFollowersList(userId, order), HttpStatus.OK);
    }

    /*
     * US 0004: Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
     * US 0008: Ordenamiento alfabético ascendente y descendente
     */
    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<FollowedListDTO> followedList(@PathVariable  Integer userId,
                                                        @RequestParam(required = false) String order) {
        return new ResponseEntity<>(sellerService.getFollowedList(userId, order), HttpStatus.OK);
    }


}
