package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.response.FollowedListResponseDto;
import com.example.socialmeli.exceptions.CantFollowYourselfException;
import com.example.socialmeli.exceptions.OrderNotFoundException;
import com.example.socialmeli.exceptions.UserNotFoundException;
import com.example.socialmeli.models.User;
import com.example.socialmeli.services.IServiceSocialMeli;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
//Controlador encargado de manejar los endpoint que tienen que ver con el usuario
public class UserController {

    IServiceSocialMeli serviceSocialMeli;

    public UserController(IServiceSocialMeli serviceSocialMeli) {
        this.serviceSocialMeli = serviceSocialMeli;
    }

    /**
     * ENDPOINT PARA CARGAR EN MEMORIA DATOS INICIALES
     * @return
     */
    @PostMapping("/createuser") //va a crear usuarios en  un arraylist
    public ResponseEntity<List<User>> createUser(){
       return new ResponseEntity<>(serviceSocialMeli.create(),HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     *  Punto 1: Hace que un usuario se encuentre siguiendo a un vendedor y devuelve ok si todo fue correcto
     * @param userId
     * @param userIdToFollow
     * @return
     * @throws UserNotFoundException
     * @throws CantFollowYourselfException
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public HttpStatus follow(@Valid @NotNull @PathVariable Integer userId,@Valid @NotNull @PathVariable Integer userIdToFollow) throws UserNotFoundException, CantFollowYourselfException {
        serviceSocialMeli.follow(userId,userIdToFollow);
        return HttpStatus.OK;
    }


    //DESARROLLADO Y FUNCIONANDO

    /**
     *  Punto 4: Obtiene la lista de seguidos que tiene un determinado usuario
     *
     * Punto 8: ordena alfabeticamente la lista de seguidos de un usuario
     * por el nombre tanto de forma ascendente como descendente
     * @param order
     * @param UserId
     * @return
     * @throws UserNotFoundException
     * @throws OrderNotFoundException
     */
    @GetMapping(value = "/{UserId}/followed/list",params = {"order"})
    public ResponseEntity<FollowedListResponseDto> followedList(@RequestParam(defaultValue = "name_asc") String order, @Valid @NotNull @PathVariable Integer UserId ) throws UserNotFoundException, OrderNotFoundException {
        return new ResponseEntity<>(serviceSocialMeli.followedList(UserId,order),HttpStatus.OK);
    }


    //DESARROLLADO Y FUNCIONANDO

    /**
     *  Punto 7: permite al usuario dejar de seguir a un vendedor
     * @param userId
     * @param userIdToUnfollow
     * @return
     * @throws UserNotFoundException
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public HttpStatus unfollow(@Valid @NotNull @PathVariable Integer userId,@Valid @NotNull @PathVariable Integer userIdToUnfollow) throws UserNotFoundException {
        serviceSocialMeli.unfollow(userId,userIdToUnfollow);
        return HttpStatus.OK;
    }










}
