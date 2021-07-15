package com.apismeli.socialmeli.controllers;

import com.apismeli.socialmeli.dtos.request.PublicationDTO;
import com.apismeli.socialmeli.dtos.response.PostBySellerDTO;
import com.apismeli.socialmeli.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //inicializar
    @GetMapping("/initializer")
    public ResponseEntity<PostBySellerDTO> initializer() throws Exception {
        return new ResponseEntity<>(postService.initializer(), HttpStatus.OK);
    }

    //005
    @PostMapping("/newpost")
    public ResponseEntity register(@Valid @RequestBody PublicationDTO publicationDTO) throws Exception {
        return postService.register(publicationDTO);
    }

    //006 y 009
    @PostMapping("/followed/{userId}/list")
    public Object showPosts(@PathVariable Integer userId, @RequestParam(value = "order", required = false) String order) throws Exception {
        return postService.showPosts(userId, order);
    }

}
