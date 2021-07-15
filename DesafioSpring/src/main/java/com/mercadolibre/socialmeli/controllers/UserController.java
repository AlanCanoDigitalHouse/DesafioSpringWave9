package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.User.UserCountDTO;
import com.mercadolibre.socialmeli.dtos.User.UserFollowedDTO;
import com.mercadolibre.socialmeli.dtos.User.UserFollowersDTO;
import com.mercadolibre.socialmeli.dtos.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.ExcepcionEqualsUserId;
import com.mercadolibre.socialmeli.exceptions.ExceptionFollower;
import com.mercadolibre.socialmeli.exceptions.ExceptionOrder;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.User;
import com.mercadolibre.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * Extra para ver los usuarios de del json cargado
     *
     * @return json de usuarios
     */
    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * US 0001: Hace que un usuario siga a un vendedor y da OK si todo fue correcto
     *
     * @param userId
     * @param userIdToFollow
     * @return UserResponseDTO
     * @throws ExceptionUserNotFound
     * @throws ExceptionFollower
     * @throws ExcepcionEqualsUserId
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public UserResponseDTO follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws ExceptionUserNotFound, ExcepcionEqualsUserId {
        return userService.follow(userId, userIdToFollow);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * US 0002: Cuenta los seguidores de un usuario
     *
     * @param userId
     * @return UserCountDTO
     * @throws ExceptionUserNotFound
     */
    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<UserCountDTO> countFollowers(@PathVariable Integer userId) throws ExceptionUserNotFound {
        UserCountDTO userCountDTO = userService.countFollowers(userId);
        return new ResponseEntity<>(userCountDTO, HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * US 0003: Lista los seguidores de un usuario
     * US 0008: Ordenamiento alfabetico
     *
     * @param userId
     * @return UserFollowersDTO
     * @throws ExceptionUserNotFound
     * @throws ExceptionOrder
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersDTO> getFollowers(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order) throws ExceptionUserNotFound, ExceptionOrder {
        UserFollowersDTO userFollowersDTO = userService.getFollowers(userId, order);
        return new ResponseEntity<>(userFollowersDTO, HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * US 0004: Lista de usuarios seguidos por un usuario
     * US 0008: Ordenamiento alfabetico
     *
     * @param userId
     * @param order
     * @return
     * @throws ExceptionUserNotFound
     * @throws ExceptionOrder
     */

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowedDTO> getFollowed(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order) throws ExceptionUserNotFound, ExceptionOrder {
        UserFollowedDTO userFollowedDTO = userService.getFollowed(userId, order);
        return new ResponseEntity<>(userFollowedDTO, HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * US 0007: Dejar de seguir a un usuario
     *
     * @param userId
     * @param userIdToUnfollow
     * @return
     * @throws ExceptionUserNotFound
     * @throws ExceptionFollower
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public UserResponseDTO unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws ExceptionUserNotFound, ExceptionFollower {
        return userService.unfollow(userId, userIdToUnfollow);
    }
}
