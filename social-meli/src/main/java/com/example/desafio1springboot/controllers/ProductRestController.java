package com.example.desafio1springboot.controllers;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.*;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    IProductService iProductService;
    public ProductRestController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    /**
     * TODO: US 0005: Da de alta una nueva publicacion
     * @param post es una publicacion con los atributos: userId, date, detail, product_id, productName, type, brand, color, notes, category, price
     * @return ResponseEntity<String>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     * @throws PostNotValidDateException Lo arroja si la fecha que se le pasa es posterior a la fecha del dia de hoy
     */
    @PostMapping("/newpost")
    public ResponseEntity<String> createNewPost(@Valid @RequestBody PostDTO post) throws UserSellerNotFoundExceptions, PostNotValidDateException {
        iProductService.createNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: US 0006 y US 0009: Retorna una lista de publicaciones realizadas por los vendedores que un usuario sigue en las ultimas dos semanas
     * @param userId es el id de un vendedor
     * @param order es el tipo de ordenamiento que va a tener el listado, la fecha del post
     * @return ResponseEntity<UserPostsResposeDTO>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     * @throws OrderNotValidException Lo arroja si la forma de ordenar no es valida (se acepta: date_asc y date_asc), date_Asc es por defecto
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<UserPostsResposeDTO> postsList2WeeksAgo(@PathVariable Integer userId, @RequestParam(defaultValue = "date_asc") String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        return new ResponseEntity<>(iProductService.listsPostsFromUser_(userId, order), HttpStatus.OK);
    }

    /**
     * TODO: US 0010: Retorna una nueva publicacion pero con promocion
     * @param post post es una publicacion con los atributos: userId, date, detail, product_id, productName, type, brand, color, notes, category, price, hasPromo, discount
     * @return ResponseEntity<String>
     * @throws PostNotValidDateException Lo arroja si la fecha que se le pasa es posterior a la fecha del dia de hoy
     */
    @PostMapping("/newpromopost")
    public ResponseEntity<String> addNewPromoPost(@Valid @RequestBody PostInPromoDTO post) throws PostNotValidDateException {
        iProductService.createNewPromoPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TODO: US 0011: Retorna la cantidad de productos en promocion de un vendedor determinado
     * @param userId es el id de un vendedor
     * @return ResponseEntity<UserSellerResponseDTO>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     */
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<UserSellerResponseDTO> countPromoPost(@PathVariable Integer userId) throws UserSellerNotFoundExceptions {
        return new ResponseEntity<>(iProductService.countProductsInPromo(userId), HttpStatus.OK);
    }

    /**
     * TODO: US 0012: Retorna los post promocionados por un usuario vendedor
     * @param userId es el id de un vendedor
     * @param order es el tipo de ordenamiento que va a tener el listado, por nombre de producto
     * @return ResponseEntity<UserPostsResposeDTO>
     * @throws UserSellerNotFoundExceptions Lo arroja si el vendedor no existe
     * @throws OrderNotValidException Lo arroja si la forma de ordenar no es valida (se acepta: nameProduct_asc y nameProduct_desc), nameProduct_asc es por defecto
     */
    @GetMapping("/{userId}/list")
    public ResponseEntity<UserPostsResposeDTO> postPromoByUser_(@PathVariable Integer userId, @RequestParam(defaultValue = "nameProduct_asc") String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        return new ResponseEntity<>(iProductService.getPostsInPromoByUser_(userId, order), HttpStatus.OK);
    }
}
