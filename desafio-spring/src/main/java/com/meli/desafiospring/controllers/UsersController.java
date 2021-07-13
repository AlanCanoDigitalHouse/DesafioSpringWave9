package com.meli.desafiospring.controllers;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.DTOs.response.FollowersCountResponseDTO;
import com.meli.desafiospring.DTOs.response.PostsListResponseDTO;
import com.meli.desafiospring.DTOs.response.FollowersListResponseDTO;
import com.meli.desafiospring.routers.Router;
import com.meli.desafiospring.services.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    UserManager userManager;

    // ACA PODRIA CREAR UN ENDPOINT QUE ME INICIALICE UNA SERIE DE USUARIOS Y POSTS
    // ES NECESARIO, AUNQUE NO LO DIGA EL ENUNCIADO
    // HAY 2 FORMAS: CREO 1 JSON Y LO LEVANTO, O DIRECTAMENTE UN METODO ESTATICO QUE INICIALIZA TODO

    // US0001
    @PostMapping(Router.FOLLOW)
    public HttpStatus follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return userManager.follow(userId, userIdToFollow);
    }

    // US0002
    @GetMapping(Router.FOLLOWERS_COUNT)
    public ResponseEntity<FollowersCountResponseDTO> followers_count(@PathVariable Integer userId) {
        FollowersCountResponseDTO followersCountDTO = new FollowersCountResponseDTO(
                userManager.getUser(userId),
                userManager.followersCount(userId)
        );

        return new ResponseEntity(followersCountDTO, HttpStatus.OK);
    }

    // US0003
    // Lista de usuarios que siguen a un vendedor (cualquier usuario)
    @GetMapping(Router.FOLLOWERS_LIST)
    public ResponseEntity<FollowersListResponseDTO> followers_list(@PathVariable Integer sellerId,
                                                                   @RequestParam(required=false) String order) {
        return null;
    }

    // US0004
    // Lista de vendedores que sigue un determinado usuario; es vendedor si tiene posts
    @GetMapping(Router.FOLLOWED_LIST)
    public ResponseEntity<FollowersListResponseDTO> sellers_followed_list(@PathVariable Integer userId,
                                                                          @RequestParam(required=false) String order) {
        // Definir un HashMap estático para usar en el repository y que mapee name_asc al ordenamiento deseado
        return null;
    }

    // US0007
    @PostMapping(Router.UNFOLLOW)
    public HttpStatus unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return userManager.unfollow(userId, userIdToFollow);
    }

}
