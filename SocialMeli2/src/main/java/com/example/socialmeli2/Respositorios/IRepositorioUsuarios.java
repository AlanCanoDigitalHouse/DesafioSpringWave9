package com.example.socialmeli2.Respositorios;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.ModelosDto.UsuarioDto;

public interface IRepositorioUsuarios {
    UsuarioDto encontrarUsuarioPorId(Integer id);

    String buscarUsuarioDB (Integer idUsuarioSeguidor , Integer idUsuarioASeguir) throws IdNoEncontrado;


}

