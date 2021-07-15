package com.example.socialmeli2.Servicios;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.ModelosDto.UsuarioDto;

public interface IServicioUsuario {
    String seguirUsuario(Integer idUsuario, Integer idUsuarioASeguir) throws IdNoEncontrado;
}
