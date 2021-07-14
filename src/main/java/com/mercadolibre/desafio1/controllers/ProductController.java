package com.mercadolibre.desafio1.controllers;

import com.mercadolibre.desafio1.dto.request.PublicationRequestDTO;
import com.mercadolibre.desafio1.dto.response.FollowedUserListResponseDTO;
import com.mercadolibre.desafio1.dto.response.PublicationResponseDTO;
import com.mercadolibre.desafio1.exceptions.DateAfterNowException;
import com.mercadolibre.desafio1.exceptions.UserNotExistException;
import com.mercadolibre.desafio1.services.interfaces.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    private final PublicationService productService;

    public ProductController(PublicationService productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<PublicationResponseDTO> newPost(@Valid @RequestBody PublicationRequestDTO publicationRequestDTO) throws UserNotExistException, DateAfterNowException {
        return new ResponseEntity<>(productService.newPost(publicationRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedUserListResponseDTO> getFollowersUserList(@Valid @PathVariable@NotNull @Positive(message = "Debe ingresar un ID mayor a 0.")  Integer userId,
                                                                                   @RequestParam(name = "order", required = false, defaultValue = "date_desc") String order) throws UserNotExistException {
        return new ResponseEntity<>(productService.getFollowersUserList(userId,order), HttpStatus.OK);
    }
}
