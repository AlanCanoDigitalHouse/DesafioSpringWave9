package com.example.socialmeli2.Controladores;

import com.example.socialmeli2.dtos.requests.PublicacionRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/")
public class ControladorPublicacion {

    @PostMapping("/newpost")
    public ResponseEntity<String> nuevoProducto(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO){
        return new ResponseEntity<>("Publicacion hecha", HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity <String> listaVendedoresDeSeguidos(@PathVariable Integer userId){
        return new ResponseEntity<>()
    }





}

