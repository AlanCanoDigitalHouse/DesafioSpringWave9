package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.PostDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserPostsResposeDTO;
import com.example.desafio1springboot.exceptions.OrderNotValidException;
import com.example.desafio1springboot.exceptions.PostNotValidDateException;
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
     * TODO: US 0005: Da de alta una nueva publicacion - VERIFICAR FECHA
     * @param
     * @return ResponseEntity<String>
     * @throws UserSellerNotFoundExceptions, PostNotValidDateException
     */
    @PostMapping("/newpost")
    public ResponseEntity<String> createNewPost(
            @Valid @RequestBody PostDTO post
            ) throws UserSellerNotFoundExceptions, PostNotValidDateException {
        iProductService.createNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0006 y US 0008: Retorna una lista de publicaciones realizadas por los vendedores que un usuario sigue en las ultimas dos semanas - ordenamiento por fecha.
     * @param userId
     * @return ResponseEntity<UserPostsResposeDTO>
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<UserPostsResposeDTO> postsList2WeeksAgo(@PathVariable Integer userId, @RequestParam(defaultValue = "date_asc") String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        return new ResponseEntity<>(iProductService.listsPostsFromUser_(userId, order), HttpStatus.OK);
    }
}
