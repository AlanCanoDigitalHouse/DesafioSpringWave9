package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.resp.FollowedDTO;
import com.mercadolibre.socialmeli.dtos.resp.FollowersDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
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

    /**
     * US 0001: Poder realizar la acción de “Follow” (seguir) a un determinado vendedor.
     * @param userId buyer ID
     * @param userIdToFollow seller ID
     * @return ResponseEntity
     * @throws SellerNotFoundException if seller is not found.
     * @throws BuyerNotFoundException if buyer is not found.
     * @throws SellerAlreadyFollowedException if seller is already followed by the buyer.
     */
    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws SellerNotFoundException, BuyerNotFoundException, SellerAlreadyFollowedException{
        sellerService.follow(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
     * @param userId buyer ID
     * @param userIdToUnfollow seller ID
     * @return ResponseEntity
     * @throws SellerNotFoundException if seller is not found.
     * @throws BuyerNotFoundException if buyer is not found.
     * @throws SellerNotFollowedException if seller is not followed by the buyer.
     */
    @PostMapping(path = "/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws SellerNotFoundException, BuyerNotFoundException, SellerNotFollowedException {
        sellerService.unfollow(userId, userIdToUnfollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * US 0002: Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor.
     * @param userId seller ID
     * @return ResponseEntity<FollowersCountDTO>
     * @throws SellerNotFoundException if seller is not found
     */
    @GetMapping(path = "/{userId}/followers/count")
    public ResponseEntity<FollowersDTO> sellerFollowersCount(@PathVariable Integer userId) throws SellerNotFoundException {
        return new ResponseEntity<>(sellerService.getFollowersCount(userId), HttpStatus.OK);
    }

    /**
     * US 0003: Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
     * US 0008: Ordenamiento alfabético ascendente y descendente
     * @param userId seller ID
     * @param order order method. Valid values are: "name_asc", "name_desc"
     * @return ResponseEntity<FollowersListDTO>
     * @throws SellerNotFoundException is seller is not found
     */
    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<FollowersDTO> sellerFollowersList(@PathVariable Integer userId,
                                                            @RequestParam(required = false) String order) throws SellerNotFoundException {
        return new ResponseEntity<>(sellerService.getFollowersList(userId, order), HttpStatus.OK);
    }

    /**
     * US 0004: Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
     * US 0008: Ordenamiento alfabético ascendente y descendente
     * @param userId  buyer ID
     * @param order order method. Valid values are: "name_asc", "name_desc"
     * @return ResponseEntity<FollowedListDTO>
     * @throws BuyerNotFoundException id buyer is not found
     */
    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<FollowedDTO> followedList(@PathVariable Integer userId,
                                                    @RequestParam(required = false) String order) throws BuyerNotFoundException {
        return new ResponseEntity<>(sellerService.getFollowedList(userId, order), HttpStatus.OK);
    }


}
