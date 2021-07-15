package com.meli.itbootcamp.controllers;

import com.meli.itbootcamp.dto.*;
import com.meli.itbootcamp.dto.request.PostRequestDTO;
import com.meli.itbootcamp.dto.request.PromoRequestDTO;
import com.meli.itbootcamp.exceptions.PostException;
import com.meli.itbootcamp.exceptions.UserException;
import com.meli.itbootcamp.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class PostRestController {

    @Autowired
    private PostServices postServices;

    /**
     * US0005- Creacion de un nuevo post
     * @param newPostPayload Datos del post que se quiere generar
     * @return Detalle de la operacion de creacion
     * @throws PostException si no es posible generar el post para el usuario en particular
     */
    @PostMapping("/newpost")
    public ResponseEntity<ResponseDTO> addNewPost(@Valid @RequestBody PostRequestDTO newPostPayload) throws PostException {
        return new ResponseEntity<>(postServices.createNewPost(newPostPayload), HttpStatus.OK);
    }

    /**
     * US0009- Listado de todas las publicaciones hechas por los vendedores que sigue un usuario, y que hayan sido
     * creada en las ultimas 2 semanas
     * @param userId usuario del que se desea obtener el listado
     * @param orderBy orden en el que se presentan las publicaciones, segun su fecha de creacion
     * @return el listado de todas las publicaciones de los vendedores.
     * @throws UserException Si el usuario no existe, o es un vendedor
     */
    @GetMapping("/followed/{userId}/list")
    public ListPostSellerDTO getPostSeller(@PathVariable Integer userId, @RequestParam("order") String orderBy) throws UserException {
        return postServices.getPostSeller(userId, orderBy);
    }

    /**
     * US0010 Creacion de una publicacion en promocion
     * @param requestPaylod datos de la nueva publicacion
     * @return detalles de la operacion
     * @throws UserException si el usuario que pretende cargar la publicacion no existe o no es vendedor
     */
    @PostMapping("/newpromopost")
    public ResponseEntity<ResponseDTO> addNewPromo(@Valid @RequestBody PromoRequestDTO requestPaylod) throws UserException {

        return new ResponseEntity<ResponseDTO>(postServices.createNewPromo(requestPaylod), HttpStatus.OK);
    }

    /**\
     * US0011- Cantidada de publicaciones del tipo promocion que genero un vendedor
     * @param userId vendedor del caul se desea obtener la cantidad
     * @return Los datos del vendedor, junto con el numero de publicaciones del tipo promocion
     */
    @GetMapping("/{userId}/countPromo/")
    public CountPromoDTO countSellerPromo(@PathVariable Integer userId) {
        return postServices.quantPromo(userId);
    }

    /**
     * US0012- Listado de todas las publicaciones en promocion que posee un vendedor
     * @param userId Vendedor de los cuales se quiere obtener el listado
     * @param orderBy orden en el que se presentan las promociones, ordenadas por el nombre de los productos que
     *                contienen
     * @return Listado de todas las promociones que tiene un vendedor, con los detalles de la misma
     */
    @GetMapping("/{userId}/list/")
    public ListPromoSellerDTO getPromoSeller(@PathVariable Integer userId,@RequestParam("order") String orderBy) {
        return postServices.getPromoSeller(userId, orderBy);
    }

    public PostServices getServices() {
        return postServices;
    }

}
