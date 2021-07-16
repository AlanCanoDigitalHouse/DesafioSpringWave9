package com.example.socialmeli2.Controladores;

import com.example.socialmeli2.dtos.requests.PublicacionRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/")
public class ControladorPublicacion {

    @PostMapping("/newpost")
    public ResponseEntity<String> nuevoProducto(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO){
        return new ResponseEntity<>("Hola mundo", HttpStatus.OK);
    }
}

