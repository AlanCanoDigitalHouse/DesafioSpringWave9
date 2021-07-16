package com.example.socialmeli2.Controladores;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.Servicios.IServicioUsuario;
import com.example.socialmeli2.dtos.responses.UsuarioCantidadResponseDTO;
import com.example.socialmeli2.dtos.responses.UsuarioListaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/users")
public class ControladorUsuario{

    private IServicioUsuario servicioUsuario;

    public ControladorUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping ("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> seguirUsuario(@PathVariable Integer userId,@PathVariable Integer userIdToFollow) throws IdNoEncontrado {
        return new ResponseEntity<>(servicioUsuario.seguirUsuario(userId, userIdToFollow),HttpStatus.OK);
    }

    @GetMapping ("/{userId}/followers/count/")
    public ResponseEntity<UsuarioCantidadResponseDTO> cantidadSeguidoresPorUsuario(@PathVariable Integer userId) {
            return new ResponseEntity<>( servicioUsuario.cantidadSeguidores(userId), HttpStatus.OK);
    }
    @GetMapping ("/{userId}/followers/list")
    public ResponseEntity<UsuarioListaResponseDTO> listaSeguidoresPorUsuario(@PathVariable Integer userId) {
        return new ResponseEntity<>( servicioUsuario.listaSeguidoresPorUsuario(userId) , HttpStatus.OK);
    }

    @GetMapping ("/{userId}/followed/list")
    public ResponseEntity<UsuarioListaResponseDTO> listaDeSeguidosPorUsuario(@PathVariable Integer userId) {
        return new ResponseEntity<>(servicioUsuario.listaDeSeguidosPorUsuario(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> dejarDeSeguirUsuario(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        return  new ResponseEntity<String>(servicioUsuario.dejarDeSeguir(userId, userIdToUnfollow), HttpStatus.OK);
    }
}

