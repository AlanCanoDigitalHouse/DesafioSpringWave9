package com.example.socialmeli2.Servicios;
import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.ModelosDto.DatosUsuariosDto;
import com.example.socialmeli2.ModelosDto.UsuarioDto;
import com.example.socialmeli2.Respositorios.IRepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicioUsuarioImp implements IServicioUsuario {
     @Autowired
     IRepositorioUsuarios repositorioUsuarios;


    @Override
    public String seguirUsuario(Integer idUsuarioSeguidor, Integer idUsuarioASeguir) throws IdNoEncontrado {
     return repositorioUsuarios.buscarUsuarioDB(idUsuarioSeguidor, idUsuarioASeguir);
    }

}