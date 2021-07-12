package com.meli.desafiospring.controllers;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.DTOs.response.FollowersCountResponseDTO;
import com.meli.desafiospring.DTOs.response.PostsListResponseDTO;
import com.meli.desafiospring.DTOs.response.FollowersListResponseDTO;
import com.meli.desafiospring.services.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UsersController {

    UserManager userManager;

    // ACA PODRIA CREAR UN ENDPOINT QUE ME INICIALICE UNA SERIE DE USUARIOS Y POSTS
    // ES NECESARIO, AUNQUE NO LO DIGA EL ENUNCIADO
    // HAY 2 FORMAS: CREO 1 JSON Y LO LEVANTO, O DIRECTAMENTE UN METODO ESTATICO QUE INICIALIZA TODO

    // US0001
    @PostMapping("/users/{userId}/follow/{userIdToFollow")
    public ResponseEntity<HttpStatus> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userManager.follow(userId, userIdToFollow));
    }

    // US0002
    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity<FollowersCountResponseDTO> followers_count(@PathVariable Integer userId) {
        return null;
    }

    // US0003
    // Lista de usuarios que siguen a un vendedor (cualquier usuario)
    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<FollowersListResponseDTO> followers_list(@PathVariable Integer sellerId) {
        return null;
    }

    // US0004
    // Lista de vendedores que sigue un determinado usuario; es vendedor si tiene posts
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<FollowersListResponseDTO> sellers_followed_list(@PathVariable Integer userId,
                                                                          @RequestBody String order) {
        // Definir un HashMap estático para usar en el repository y que mapee name_asc al ordenamiento deseado
        return null;
    }

    // US0005
    @PostMapping("/products/newpost")
    public ResponseEntity<HttpStatus> new_post(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // US0006, US0008, US0009
    @GetMapping("/products/followed/{userId}/list")
    public PostsListResponseDTO sellers_followed_post_list_last2weeks(@PathVariable Integer userId) {
        return null;
    }

    // US0007
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<HttpStatus> unfollow(@PathVariable Integer userId) {
        return null;
    }

}
