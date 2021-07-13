package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserResponseDTO;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import com.example.desafio1springboot.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
