package com.socialmeli.controllers;

import com.socialmeli.dtos.requests.PostRequestDTO;
import com.socialmeli.dtos.requests.PromoPostRequestDTO;
import com.socialmeli.dtos.responses.CountPromoResponseDTO;
import com.socialmeli.dtos.responses.LastProductsResponseDTO;
import com.socialmeli.dtos.responses.ListPromoResponseDTO;
import com.socialmeli.exceptions.ApiException;
import com.socialmeli.services.SocialMeliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/products")
public class SocialMeliProductController {

    SocialMeliService socialMeliService;

    public SocialMeliProductController(SocialMeliService socialMeliService){
        this.socialMeliService = socialMeliService;
    }

    /**
     * Obtener el listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas.
     * @param userId id del usuario.
     * @return lista de productos publicados por los vendedores que sigue el usuario.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<LastProductsResponseDTO> lastProducts(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId){

        return new ResponseEntity<>(socialMeliService.getLastProducts(Integer.valueOf(userId)), HttpStatus.OK);
    }

    /**
     * Obtener el listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas.
     * @param userId id del usuario.
     * @param order indica el orden de los valores a devolver pude ser date_asc|date_desc
     * @return lista de productos publicados por los vendedores que sigue el usuario.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping(value = "/followed/{userId}/list", params = {"order"})
    public ResponseEntity<LastProductsResponseDTO> orderLastProducts(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId,
                                                                     @RequestParam @Pattern(regexp = "date_asc|date_desc", message = "order must be date_asc or date_desc") String order){
        return new ResponseEntity<>(socialMeliService.getLastProducts(Integer.valueOf(userId), order), HttpStatus.OK);
    }

    /**
     * Dar de alta una nueva publicación.
     * @param request publicación a dar de alta.
     * @throws ApiException, ConstraintViolationException, MethodArgumentNotValidException
     */
    @PostMapping("/newpost")
    public void createPromoPost(@Valid @RequestBody PostRequestDTO request) {

        socialMeliService.createPost(request);
    }

    /**
     * Dar de alta una publicación de un nuevo producto en promoción.
     * @param request publicación a dar de alta.
     * @throws ApiException, ConstraintViolationException, MethodArgumentNotValidException
     */
    @PostMapping("/newpromopost")
    public void createPromoPost(@Valid @RequestBody PromoPostRequestDTO request) {

        socialMeliService.createPostPromo(request);
    }

    /**
     * Obtener la cantidad de productos en promoción de un determinado vendedor.
     * @param userId id del usuario.
     * @return cantidad de productos en promoción.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping("/{userId}/countPromo/")
    public ResponseEntity<CountPromoResponseDTO> countPromo(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId) {

        return new ResponseEntity<>(socialMeliService.countPromo(Integer.valueOf(userId)), HttpStatus.OK);
    }

    /**
     * Obtener la lista de productos en promoción de un determinado vendedor.
     * @param userId id del usuario.
     * @return lista de productos en promoción.
     * @throws ApiException, ConstraintViolationException
     */
    @GetMapping("/{userId}/list/")
    public ResponseEntity<ListPromoResponseDTO> listPromo(@PathVariable @Pattern(regexp = "[0-9]+", message = "userId must be a number") String userId) {

        return new ResponseEntity<>(socialMeliService.listPromo(Integer.valueOf(userId)), HttpStatus.OK);
    }

}
