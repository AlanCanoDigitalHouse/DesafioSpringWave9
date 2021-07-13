package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.dtos.SellerPostListDTO;
import com.mercadolibre.socialmeli.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    /*
     * US 0005: Dar de alta una nueva publicación
     */
    @PostMapping(path = "/newpost")
    public ResponseEntity newPost(@RequestBody @Valid PostDTO postDTO) {
        productService.createPost(postDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
     * US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas
     * dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
     *
     * US 0009: Ordenamiento por fecha ascendente y descendente
     */
    @GetMapping(path = "/followed/{userId}/list")
    public ResponseEntity<SellerPostListDTO> twoWeeksPostList(@PathVariable Integer userId,
                                                              @RequestParam(required = false, defaultValue = "fecha_desc") String order) {
        return new ResponseEntity<>(productService.twoWeeksSellerPosts(userId, order), HttpStatus.OK);
    }

}
