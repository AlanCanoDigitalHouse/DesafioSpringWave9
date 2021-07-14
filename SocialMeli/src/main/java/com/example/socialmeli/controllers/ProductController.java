package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.request.PostPromoRequestDTO;
import com.example.socialmeli.dtos.request.PostRequestDTO;
import com.example.socialmeli.dtos.response.PostPromoResponseDTO;
import com.example.socialmeli.dtos.response.PostResponseDTO;
import com.example.socialmeli.dtos.response.SuccessResponseDTO;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.exceptions.DateParserException;
import com.example.socialmeli.exceptions.OrderInvalidFormatException;
import com.example.socialmeli.exceptions.SameUserException;
import com.example.socialmeli.services.interfaces.PostServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final PostServices postServices;

    public ProductController(PostServices postServices) {
        this.postServices = postServices;
    }

    /**
     * US-0005: Metodo para aregar un nuevo post a un usuario
     *
     * @param postRequestDTO post a dar de alta
     * @return El resultado de la operacion de alta de producto
     * @throws DataNotFound        Si el usuario no existe
     * @throws DateParserException Si el formato de fecha no es valido
     **/
    @PostMapping("/newpost")
    public ResponseEntity<SuccessResponseDTO> newPost(@Valid @RequestBody PostRequestDTO postRequestDTO) throws DataNotFound, DateParserException {
        return new ResponseEntity<>(postServices.newPost(postRequestDTO), HttpStatus.OK);
    }

    /**
     * US-0006: Obtener la lista de post realizados por los vendedores que sigue userId
     *
     * @param userId El usuario que desea conocer la lista post de los vendedores que sigue
     * @return lista de productos publicados por los vendedores que sigue el usuario.
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<List<PostResponseDTO>> getPostByUser(@PathVariable Integer userId) throws DataNotFound {
        return new ResponseEntity<>(postServices.getPostByUserId(userId), HttpStatus.OK);
    }

    /**
     * US-0009: Obtener la lista de post realizados por los vendedores que sigue userId
     *
     * @param userId El usuario que desea conocer la lista post de los vendedores que sigue
     * @param order  Indica el orden de los valores a devolver pude ser date_asc|date_desc
     * @return lista de productos publicados por los vendedores que sigue el usuario.
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping(value = "/followed/{userId}/list", params = {"order"})
    public ResponseEntity<List<PostResponseDTO>> getPostByUserOrder(@PathVariable Integer userId,
                                                                    @RequestParam String order) throws DataNotFound, OrderInvalidFormatException {
        return new ResponseEntity<>(postServices.getPostByUserId(userId, order), HttpStatus.OK);
    }

    /**
     * US-0010: Metodo para aregar un nuevo post en promoo a un usuario
     *
     * @param postRequestDTO post a dar de alta
     * @return El resultado de la operacion de seguimiento
     * @throws DataNotFound        Si el usuario no existe
     * @throws DateParserException Si el formato de fecha no es valido
     **/
    @PostMapping("/newpromopost")
    public ResponseEntity<SuccessResponseDTO> newPromoPost(@Valid @RequestBody PostPromoRequestDTO postRequestDTO) throws DataNotFound, DateParserException {
        return new ResponseEntity<>(postServices.newPromoPost(postRequestDTO), HttpStatus.OK);
    }


    /**
     * US-0011: Obtener la cantidad de post en promo de un usuario
     *
     * @param userId El usuario que desea conocer la cantidad de post en promo
     * @return Datos del usuario, más cantidad de post en promo
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping("{userId}/countPromo/")
    public ResponseEntity<PostPromoResponseDTO> getPromoByUser(@PathVariable Integer userId) throws DataNotFound {
        return new ResponseEntity<>(postServices.getPostPromoByUser(userId), HttpStatus.OK);
    }

    /**
     * US-0012: Obtener la lista de post en promo de un usuario
     *
     * @param userId El usuario que desea conocer la lista de post en promo
     * @return Datos del usuario, más lista de post en promo
     * @throws DataNotFound Si el usuario no existe
     **/
    @GetMapping(value = "/{userId}/list/")
    public ResponseEntity<List<PostResponseDTO>> getPostPromoDetailByUser(@PathVariable Integer userId) throws DataNotFound {
        return new ResponseEntity<>(postServices.getPostPromoDetailByUser(userId), HttpStatus.OK);
    }
}
