package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.response.*;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.exceptions.OrderInvalidFormatException;
import com.example.socialmeli.exceptions.SameUserException;
import com.example.socialmeli.services.interfaces.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    /**
     * US-0001: Metodo para empezar a seguir a un nuevo vendedor
     *
     * @param userId         El usuario que desea comenzar a seguir un nuevo vendedor
     * @param userIdToFollow El usuario que el UserId desea comenzar a seguir
     * @return El resultado de la operacion de seguimiento
     * @throws DataNotFound      Si el usuario no existe
     * @throws SameUserException Si el usuario userId es el mismo que UserIdToFollow
     **/
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<SuccessResponseDTO> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow)
            throws DataNotFound, SameUserException {
        return new ResponseEntity<>(userServices.followUser(userId, userIdToFollow), HttpStatus.OK);
    }

    /**
     * US-0002: Obtener la cantidad de seguidores de un usuario
     *
     * @param userId El usuario que desea conocer la cantidad de seguidores
     * @return Datos del usuario, más cantidad de seguidores
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity<UserFollowersDTO> getCountFollowers(@PathVariable String userId) throws DataNotFound {
        return new ResponseEntity<>(userServices.findFollowersByUser(userId), HttpStatus.OK);
    }

    /**
     * US-0003: Obtener la lista de seguidores de un usuario
     *
     * @param userID El usuario que desea conocer la lista de seguidores
     * @return Datos del usuario, más la lista de seguidores
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<UserFollowersDetailsDTO> getFollowers(@PathVariable(value = "UserID") String userID) throws DataNotFound {
        return new ResponseEntity<>(userServices.findFollowersDetailByUser(userID), HttpStatus.OK);
    }

    /**
     * US-0004: Obtener la lista de seguidos de un usuario
     *
     * @param userID El usuario que desea conocer la lista de seguidos
     * @return Datos del usuario, más la lista de seguidos
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<UserFollowedDetailsDTO> getFollowed(@PathVariable(value = "UserID") String userID) throws DataNotFound {
        return new ResponseEntity<>(userServices.findFollowedDetailByUser(userID), HttpStatus.OK);
    }

    /**
     * US-0007: Dejar de seguir a un usuario
     *
     * @param userId           El usuario que desea dejar de seguir a otro
     * @param userIdToUnfollow El usuario al que se desea dejar de seguir
     * @return Resultado de la operacion
     * @throws DataNotFound      Si el usuario no existe
     * @throws SameUserException Si el usuario userId es el mismo que UserIdToFollow
     **/
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<SuccessResponseDTO> unFollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow)
            throws DataNotFound, SameUserException {
        return new ResponseEntity<>(userServices.unFollowUser(userId, userIdToUnfollow), HttpStatus.OK);
    }

    /**
     * US-0008: Obtener la lista de seguidores de un usuario
     *
     * @param UserID El usuario que desea conocer la lista de seguidores
     * @param order  indica el orden de los valores a devolver pude ser name_asc|name_desc
     * @return Datos del usuario, más la lista de seguidores
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping(value = "/{UserID}/followers/list", params = {"order"})
    public ResponseEntity<UserFollowersDetailsDTO> getFollowersOrder(@PathVariable Integer UserID,
                                                                     @RequestParam String order) throws DataNotFound, OrderInvalidFormatException {
        return new ResponseEntity<>(userServices.findFollowersDetailByUser(UserID, order), HttpStatus.OK);
    }

    /**
     * US-0008: Obtener la lista de seguidos de un usuario
     *
     * @param userID El usuario que desea conocer la lista de seguidos
     * @param order  indica el orden de los valores a devolver pude ser name_asc|name_desc
     * @return Datos del usuario, más la lista de seguidos
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping(value = "/{UserID}/followed/list", params = {"order"})
    public ResponseEntity<UserFollowedDetailsDTO> getFollowedOrder(@PathVariable(value = "UserID") Integer userID,
                                                                   @RequestParam String order) throws DataNotFound, OrderInvalidFormatException {
        return new ResponseEntity<>(userServices.findFollowedDetailByUser(userID, order), HttpStatus.OK);
    }

    /**
     * Obtener la lista de usuarios de SocialMeli
     *
     * @return Los usuarios de socialMeli
     **/
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return new ResponseEntity<>(userServices.findAll(), HttpStatus.OK);
    }

    /**
     * Agregar un nuevo usuario
     *
     * @param userName nombre de usuario del nuevo integrante de socialMeli
     * @return Resultado de la accion
     **/
    @PostMapping("/{userName}")
    public ResponseEntity<UserResponseDTO> addUser(@PathVariable String userName) {
        return new ResponseEntity<>(userServices.save(userName), HttpStatus.OK);
    }

}
