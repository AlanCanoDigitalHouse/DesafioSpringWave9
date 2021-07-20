package com.example.socialmeli2.Respositorios;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.Modelos.DatosUsuarios;
import com.example.socialmeli2.Modelos.Usuario;

import java.util.List;

public interface IRepositorioUsuarios {
    Usuario encontrarUsuarioPorId(Integer id);

    String seguirUsuario(Integer idUsuarioSeguidor , Integer idUsuarioASeguir) throws IdNoEncontrado;

    void dejarDeSeguirUsuario(Usuario usuario, Usuario unfollowUsuario);

}

