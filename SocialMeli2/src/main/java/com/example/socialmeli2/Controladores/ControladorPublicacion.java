package com.example.socialmeli2.Controladores;

import com.example.socialmeli2.Servicios.IServicioPublicacion;
import com.example.socialmeli2.Servicios.IServicioUsuario;
import com.example.socialmeli2.dtos.requests.PublicacionRequestDTO;
import com.example.socialmeli2.dtos.responses.ListadoPublicacionesResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/")
public class ControladorPublicacion {

    private IServicioPublicacion servicioPublicacion;
    private IServicioUsuario servicioUsuario;

    public ControladorPublicacion(IServicioPublicacion servicioPublicacion,IServicioUsuario servicioUsuario) {
        this.servicioPublicacion= servicioPublicacion;
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping("/newpost")
    public ResponseEntity<String> nuevoProducto(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO){
        return new ResponseEntity<>("Publicacion hecha", HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity <ListadoPublicacionesResponseDTO> listaVendedoresDeSeguidos(@PathVariable Integer userId){
        return new ResponseEntity<>(servicioPublicacion.listadoPublicacionePorVendedor(userId),HttpStatus.OK);
    }





}

