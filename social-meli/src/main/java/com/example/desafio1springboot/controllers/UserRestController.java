package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {
    IUserService iUserService;
    public UserRestController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * TODO: US 0001: Hace que un usuario cliente siga a un vendedor
     * @param userId id del cliente que va a seguir al vendedor
     * @param userIdToFollow id del vendedor que va a seguir el cliente
     * @return ResponseEntity<String>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     * @throws UserAlreadyFollowingSellerException Lo arroja si el cliente ya esta siguiendo al vendedor
     */
    @GetMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followSeller( @PathVariable Integer userId, @PathVariable Integer userIdToFollow ) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException {
        iUserService.newFollowClient_ForSeller_(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: US 0002: Retorna la cantidad de clientes que siguien a cierto vendedor
     * @param userId es el id del vendedor
     * @return ResponseEntity<UserSellerResponseDTO>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserSellerResponseDTO> followers(
            @PathVariable Integer userId
    ) throws UserSellerNotFoundExceptions {
        return new ResponseEntity<>(iUserService.countFollowersForUserSeller_(userId), HttpStatus.OK);
    }

    /**
     * TODO: US 0003 y 0008: Devuelve un listado de todos los usuarios que siguen a un vendedor
     * @param userId es el id del cliente
     * @param order es el tipo de ordenamiento que va a tener el listado, segun el nombre del cliente
     * @return ResponseEntity<UserSellerDTO>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     * @throws OrderNotValidException Lo arroja si la forma de ordenar no es valida (se acepta: name_asc y name_desc), name_asc es por defecto
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserSellerDTO> followersList(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        return new ResponseEntity<>(iUserService.followersClientListBySeller_(userId, order), HttpStatus.OK);
    }

    /**
     * TODO: US 0004: Retorna un listado con todos los vendedores que siga un usuario, en caso contrario [].
     * @param userId es el id de un usuario cliente
     * @return ResponseEntity<UserClientDTO>
     * @throws UserClientDoesNotExistsException Lo arroja si un usuario cliente no existe
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserClientDTO> followedList(@PathVariable Integer userId ) throws UserClientDoesNotExistsException {
        return new ResponseEntity<>(iUserService.followedSellersListByClient_(userId), HttpStatus.OK);
    }

    /**
     * TODO: US 0007: Realiza la accion de que un cliente deja de seguir a un vendedor
     * @param userId es el id del cliente
     * @param userIdToUnfollow es el id del vendedor
     * @return ResponseEntity<String>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     * @throws UserClientNotFollowingSellerException Lo arroja si el cliente no se encuentra siguiendo al vendedor
     */
    @GetMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unfollowSeller(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow ) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException {
        iUserService.unfollowSeller(userId, userIdToUnfollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
