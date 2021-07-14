package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.*;
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
    public ResponseEntity<String> createNewPost(@Valid @RequestBody PostDTO post) throws UserSellerNotFoundExceptions, PostNotValidDateException {
        iProductService.createNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0006 y US 0009: Retorna una lista de publicaciones realizadas por los vendedores que un usuario sigue en las ultimas dos semanas - ordenamiento por fecha.
     * @param userId
     * @return ResponseEntity<UserPostsResposeDTO>
     * @throws UserSellerNotFoundExceptions
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<UserPostsResposeDTO> postsList2WeeksAgo(@PathVariable Integer userId, @RequestParam(defaultValue = "date_asc") String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        return new ResponseEntity<>(iProductService.listsPostsFromUser_(userId, order), HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0010: Retorna una nueva publicacion pero en promocion, si no se le pasa atributo hasPromo o discount automaticamente lo pone en false y 0
     * @param post
     * @return ResponseEntity<String>
     * @throws PostNotValidDateException
     */
    @PostMapping("/newpromopost")
    public ResponseEntity<String> addNewPromoPost(@Valid @RequestBody PostInPromoDTO post) throws PostNotValidDateException {
        iProductService.addNewPromoPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0011: Retorna la cantidad de productos en promocion de un vendedor determinado
     * @param userId
     * @return ResponseEntity<String>
     * @throws PostNotValidDateException
     */
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<UserSellerResponseDTO> countPromoPost(@PathVariable Integer userId) throws UserSellerNotFoundExceptions {
        return new ResponseEntity<>(iProductService.countProductsInPromo(userId), HttpStatus.OK);
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * TODO: US 0012: Retorna los post promocionados por un usuario
     * @param userId
     * @return ResponseEntity<String>
     * @throws PostNotValidDateException, OrderNotValidException
     */
    @GetMapping("/{userId}/list")
    public ResponseEntity<UserPostsResposeDTO> postPromoByUser_(@PathVariable Integer userId, @RequestParam(defaultValue = "nameProduct_asc") String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        return new ResponseEntity<>(iProductService.getPostsInPromoByUser_(userId, order), HttpStatus.OK);
    }
}
