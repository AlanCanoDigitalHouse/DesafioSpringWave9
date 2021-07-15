package com.mercado_libre.bootcamp.spring.desafio.controllers;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.SellerInformationResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.UserInformationResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.services.seller.SellerServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")
@AllArgsConstructor
public class InformationControllers {

    private UserServiceImpl userService;

    private SellerServiceImpl sellerService;

    @GetMapping("/users")
    public ResponseEntity<UserInformationResponseDTO> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/sellers")
    public ResponseEntity<SellerInformationResponseDTO> getSellers() {
        return new ResponseEntity<>(sellerService.getSellers(), HttpStatus.OK);
    }
}
