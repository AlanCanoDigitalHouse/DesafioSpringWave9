package com.mercadolibre.desafio.demo.controller;

import com.mercadolibre.desafio.demo.dtos.request.NewPostDTO;
import com.mercadolibre.desafio.demo.dtos.request.NewPostPromDTO;
import com.mercadolibre.desafio.demo.dtos.response.SuccessfullyResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicCountResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicPromoListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicsFollowedDTO;
import com.mercadolibre.desafio.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * TODO: US 0005 - Dar de alta una nueva publicación
     * POST /products/newpost
     * @param newPostDTO
     * @return
     */
    @PostMapping("/newpost")
    public ResponseEntity<SuccessfullyResponseDTO> newPost(@Valid @RequestBody NewPostDTO newPostDTO) {
        return new ResponseEntity<SuccessfullyResponseDTO>(productService.createPost(newPostDTO),HttpStatus.OK);
    }

    /**
     * TODO: US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
     * GET /products/followed/{userId}/list
     * @param userId
     * @param order
     * @return
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PublicsFollowedDTO> publicFollowedList(@PathVariable(value = "userId") Integer userId,@RequestParam(value = "order",required = false) String order){
        return new ResponseEntity<>(this.productService.listPublicsFollowed(userId,order),HttpStatus.OK);
    }

    /**
     * TODO: US 0010: Llevar a cabo la publicación de un nuevo producto en promoción
     * POST /products/newpromopost
     * @param newPostPromDTO
     * @return
     */
    @PostMapping("/newpromopost")
    public ResponseEntity<SuccessfullyResponseDTO> newPost(@Valid @RequestBody NewPostPromDTO newPostPromDTO) {
        return new ResponseEntity<SuccessfullyResponseDTO>(productService.createPromoPost(newPostPromDTO),HttpStatus.OK);
    }

    /**
     * TODO: US 0011: Obtener la cantidad de productos en promoción de un determinado vendedor
     * GET /products/{userId}/countPromo/
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<PublicCountResponseDTO> publicCountProm(@PathVariable(value = "userId") Integer userId) {
        return new ResponseEntity<>(productService.countPromoPublic(userId),HttpStatus.OK);
    }

    /**
     * TODO: US 0012: Obtener un listado de todos los productos en promoción de un determinado vendedor
     * GET /products/{userId}/list/
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/list")
    public ResponseEntity<PublicPromoListResponseDTO> listPromo(@PathVariable(value = "userId") Integer userId){
        return new ResponseEntity<>(productService.listPromoPublics(userId), HttpStatus.OK);
    }

}


