package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.*;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
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

    /**
     * US 0005: Dar de alta una nueva publicación
     * @param postDTO post payload
     * @return ResponseEntity
     * @throws SellerNotFoundException if seller is not found
     */
    @PostMapping(path = "/newpost")
    public ResponseEntity newPost(@RequestBody @Valid PostDTO postDTO) throws SellerNotFoundException {
        productService.createPost(postDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas
     * dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
     * US 0009: Ordenamiento por fecha ascendente y descendente
     * @param userId buyer ID
     * @param order order method. Valid values are: "date_asc", "date_desc"
     * @return ResponseEntity<SellerPostListDTO>
     * @throws BuyerNotFoundException if buyer is not found
     */
    @GetMapping(path = "/followed/{userId}/list")
    public ResponseEntity<SellerPostListDTO> twoWeeksPostList(@PathVariable Integer userId,
                                                              @RequestParam(required = false, defaultValue = "fecha_desc") String order) throws BuyerNotFoundException {
        return new ResponseEntity<>(productService.twoWeeksSellerPosts(userId, order), HttpStatus.OK);
    }

    /**
     * US 0010: Llevar a cabo la publicación de un nuevo producto en promoción
     * @param promoPostDTO promo post payload
     * @return ResponseEntity
     * @throws SellerNotFoundException if seller is not found
     */
    @PostMapping(path = "/newpromopost")
    public ResponseEntity newPromoPost(@RequestBody @Valid PromoPostDTO promoPostDTO) throws SellerNotFoundException {
        productService.createPost(promoPostDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * US 0011: Obtener la cantidad de productos en promoción de un determinado vendedor
     * @param userId seller id
     * @return ResponseEntity<PromoProductsCountDTO>
     * @throws SellerNotFoundException if seller is not found
     */
    @GetMapping(value = "/{userId}/countPromo")
    public ResponseEntity<PromoProductsCountDTO> promoCount(@PathVariable Integer userId) throws SellerNotFoundException {
        return new ResponseEntity<>(productService.getPromoProductsCount(userId), HttpStatus.OK);
    }

    /**
     * US 0012: Obtener un listado de todos los productos en promoción de un determinado vendedor
     * @param userId seller id
     * @return ResponseEntity<SellerPromoPostListDTO>
     * @throws SellerNotFoundException is seller is not found
     */
    @GetMapping(value = "/{userId}/list")
    public ResponseEntity<SellerPromoPostListDTO> promoPostList(@PathVariable Integer userId) throws SellerNotFoundException {
        return new ResponseEntity<>(productService.getPromoPosts(userId), HttpStatus.OK);
    }

}
