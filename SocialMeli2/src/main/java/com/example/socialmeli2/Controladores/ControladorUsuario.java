package com.example.socialmeli2.Controladores;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.ModelosDto.UsuarioDto;
import com.example.socialmeli2.Servicios.IServicioUsuario;
import com.example.socialmeli2.Servicios.ServicioUsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorUsuario{
    @Autowired
    private IServicioUsuario servicioUsuario;

    @PostMapping ("/users/{userId}/follow/{userIdToFollow}")
    public String seguirUsuario(@PathVariable Integer userId,
                                @PathVariable Integer userIdToFollow) throws IdNoEncontrado {
        return servicioUsuario.seguirUsuario(userId, userIdToFollow);
    }

    @GetMapping ("/users/{userId}/followers/count/")
    public void  cantidadSeguidoresPorUsuario(@PathVariable Integer idUsuario) {
        
    }
    @GetMapping ("/users/{UserID}/followers/list")
    public void listaSeguidoresPorUsuario(@PathVariable Integer idUsuario) {

    }

    @GetMapping ("/users/{UserID}/followed/list")
    public void listaSeguidosPorUsuario(@PathVariable Integer idUsuario) {

    }
}

