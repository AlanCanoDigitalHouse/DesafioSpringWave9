package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.request.PostDto;
import com.example.socialmeli.dtos.response.*;
import com.example.socialmeli.exceptions.OrderNotFoundException;
import com.example.socialmeli.exceptions.UserNotFoundException;
import com.example.socialmeli.services.IServiceSocialMeli;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
//Controlador que se encarga de manejar los endpoint de los vendedores
public class SellerController {

    IServiceSocialMeli serviceSocialMeli;

    public SellerController(IServiceSocialMeli serviceSocialMeli) {
        this.serviceSocialMeli = serviceSocialMeli;
    }


    //DESARROLLADO Y FUNCIONANDO
    /**
     *  Punto 2: Obtiene el resultado de de la cantidad de usuarios que siguen a un determinado vendedor
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity<FollowersCountResponseDto> countFollowers(@Valid @NotNull @PathVariable Integer userId) throws UserNotFoundException {
        return new ResponseEntity<>(serviceSocialMeli.countFollower(userId),HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     *  Punto 3: Obtiene un listado de todos los usuarios que siguen a un determinado vendedor
     *
     * Punto 8: ordena alfabeticamente la lista de seguidores de un vendedor
     *   por el nombre tanto de forma ascendente como descendente
     * @param order
     * @param userID
     * @return
     * @throws OrderNotFoundException
     */
    @GetMapping(value = "/users/{userID}/followers/list",params = {"order"})
    public ResponseEntity<FollowersListResponseDto> getFollowers(@RequestParam(defaultValue = "name_asc") String order,@Valid @NotNull @PathVariable Integer userID) throws OrderNotFoundException {
        return new ResponseEntity<>(serviceSocialMeli.getFollowers(userID,order),HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     *   Punto 5: Dar de alta una nueva publicación
     * @param postDto
     * @return
     * @throws UserNotFoundException
     */
    @PostMapping("/products/newpost")
    public ResponseEntity newProduct(@Valid @RequestBody PostDto postDto) throws UserNotFoundException {
        return new ResponseEntity(serviceSocialMeli.saveNewPost(postDto),HttpStatus.OK);
    }

    //EN DESARROLLO

    /**
     *  Punto 6: obtiene un listado de las publicaciones realizadas por los vendedores
     * que un usuario sigue en las ultimas dos semanas, ordena de la fecha mas reciente
     * a la mas antigua.
     *
     * Punto 9: ordena la lista de posteos de un vendedor por fecha ascendente o descendente.
     * @param order
     * @param userId
     * @return
     * @throws UserNotFoundException
     * @throws OrderNotFoundException
     */
    @GetMapping(value = "/products/followed/{userId}/list", params={"order"})
    public ResponseEntity<PostResponseDto> getPost(@RequestParam(defaultValue = "date_desc") String order,@Valid @NotNull@PathVariable Integer userId) throws UserNotFoundException, OrderNotFoundException {
        return new ResponseEntity<>(serviceSocialMeli.getPost(userId,order),HttpStatus.OK);
    }









}
