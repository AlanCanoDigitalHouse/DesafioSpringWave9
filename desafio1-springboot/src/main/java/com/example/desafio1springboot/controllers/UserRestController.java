package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.services.IUserService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserRestController {
    IUserService iUserService;
    public UserRestController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0001: Hace que un usuario siga a un vendedor, si este no existe entonces retorna un BAD_GATEWAY
     * @param userId
     * @param userIdToFollow
     * @return ResponseEntity<String>
     * @throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException
     */
    @GetMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> follow( @PathVariable Integer userId, @PathVariable Integer userIdToFollow ) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException {
        iUserService.addNewFollowerToSellerUser(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0002: Retorna la cantidad de clientes que tiene un vendedor, si este no se encuentra devuelve un BAD_GATEWAY
     * @param userId
     * @return ResponseEntity<UserSellerResponseDTO>
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserSellerResponseDTO> followers(
            @PathVariable Integer userId
    ) throws UserSellerNotFoundExceptions {
        return new ResponseEntity<>(iUserService.countFollowersForUser_(userId), HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0003 y 0008: Devuelve un listado de todos los usuarios que siguen a un vendedor, el order automatico es ascendete.
     * @param userId, order
     * @return ResponseEntity<UserSellerDTO>
     * @throws UserSellerNotFoundExceptions, OrderUserNameNotValidException
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserSellerDTO> followersList(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order) throws UserSellerNotFoundExceptions, OrderUserNameNotValidException {
        return new ResponseEntity<>(iUserService.followersList(userId, order), HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0004: Retorna un listado con todos los vendedores que siga un usuario, en caso contrario [].
     * @param userId
     * @return ResponseEntity<UserClientDTO>
     * @throws UserClientDoesNotExistsException
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserClientDTO> followedList(@PathVariable Integer userId ) throws UserClientDoesNotExistsException {
        return new ResponseEntity<>(iUserService.followedListByClient_(userId), HttpStatus.OK);
    }

    /**
     * TODO: US 0007: Realiza la accion de unfollow, si el vendedor no existe tira excepcion al igual si el cliente no sigue al vendedor
     * @param userId
     * @return ResponseEntity<String>
     * @throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException
     */
    @GetMapping("/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity<String> unfollowSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow ) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException {
        iUserService.unfollowSeller_By_(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: US 0008: Retorna un listado con todos los vendedores que siga un usuario, en caso contrario [].
     * @param userId
     * @return ResponseEntity<UserClientDTO>
     * @throws UserClientDoesNotExistsException
     */
    /*@GetMapping("/{userId}/followers/list")
    public ResponseEntity<String> orderFollowersList(@PathVariable Integer userId, @RequestParam(defaultValue = "World") String order) {
        var o = order;
        return new ResponseEntity<>(o ,HttpStatus.OK);
    }*/
}
