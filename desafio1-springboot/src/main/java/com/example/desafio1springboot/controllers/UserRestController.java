package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.UserAlreadyFollowingSellerException;
import com.example.desafio1springboot.exceptions.UserClientDoesNotExistsException;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import com.example.desafio1springboot.services.IUserService;
import lombok.Getter;
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
     * TODO: US 0003: Devuelve un listado de todos los usuarios que siguen a un vendedor
     * @param userId
     * @return ResponseEntity<UserSellerDTO>
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserSellerDTO> followersList(@PathVariable Integer userId ) throws UserSellerNotFoundExceptions {
        return new ResponseEntity<>(iUserService.followersList(userId), HttpStatus.OK);
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
}
