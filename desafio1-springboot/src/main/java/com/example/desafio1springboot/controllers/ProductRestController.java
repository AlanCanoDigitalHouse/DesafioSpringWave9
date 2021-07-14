package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import com.example.desafio1springboot.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    IProductService iProductService;

    public ProductRestController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("/showPost")
    public List<PostDTO> showPosts() {
        return iProductService.show();
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0005: Da de alta una nueva publicacion
     * @param
     * @return ResponseEntity<String>
     * @throws ApiExceptionControllerAdvice
     */
    @PostMapping("/newpost")
    public ResponseEntity<String> createNewPost(
            @Valid @RequestBody PostDTO post
            ) throws UserSellerNotFoundExceptions {
        iProductService.createNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: US 0006: Retorna una lista de publicaciones realizadas por los vendedores que un usuario sigue en las ultimas dos semanas - ordenamiento por fecha.
     * @param
     * @return ResponseEntity<>
     * @throws
     */
    @GetMapping("/followed/{userId}/list")
    public String postsList2WeeksAgo(@PathVariable Integer userId) {
        return "ah";
    }
}
