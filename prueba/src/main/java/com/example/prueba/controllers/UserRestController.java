package com.example.prueba.controllers;

import com.example.prueba.dtos.UserSellerDTO;
import com.example.prueba.dtos.responseDTO.UserResponseDTO;
import com.example.prueba.exceptions.UserSellerNotFoundExceptions;
import com.example.prueba.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> follow( @PathVariable Integer userId, @PathVariable Integer userIdToFollow ) throws UserSellerNotFoundExceptions {
        return iUserService.followSeller(userId, userIdToFollow);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0002: Retorna la cantidad de clientes que tiene un vendedor, si este no se encuentra devuelve un BAD_GATEWAY
     * @param userId
     * @return UserResponseDTO
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/{userId}/followers/count")
    public UserResponseDTO followers(
            @PathVariable Integer userId
    ) throws UserSellerNotFoundExceptions {
        return iUserService.countFollowersForUser_(userId);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0003: Devuelve un listado de todos los usuarios que siguen a un vendedor
     * @param userId
     * @return UserSellerDTO
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/{userId}/followers/list")
    public UserSellerDTO followersList(@PathVariable Integer userId ) throws UserSellerNotFoundExceptions {
        return iUserService.followersList(userId);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0004:
     * @param userId
     * @return UserSellerDTO
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/{userId}/followed/list")
    public UserSellerDTO followedList( @PathVariable Integer userId ) throws UserSellerNotFoundExceptions {
        return iUserService.followersList(userId);
    }
}