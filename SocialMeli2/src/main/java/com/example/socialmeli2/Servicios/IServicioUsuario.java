package com.example.socialmeli2.Servicios;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import com.example.socialmeli2.Modelos.DatosUsuarios;
import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.dtos.responses.ListadoPublicacionesResponseDTO;
import com.example.socialmeli2.dtos.responses.UsuarioCantidadResponseDTO;
import com.example.socialmeli2.dtos.responses.UsuarioListaResponseDTO;

import java.util.List;

public interface IServicioUsuario {

    String seguirUsuario(Integer idUsuario, Integer idUsuarioASeguir) throws IdNoEncontrado;

    Usuario traerUsuario(Integer id);

    UsuarioCantidadResponseDTO cantidadSeguidores(Integer id);

    UsuarioListaResponseDTO listaSeguidoresPorUsuario(Integer id);

    UsuarioListaResponseDTO listaDeSeguidosPorUsuario(Integer id);

    String dejarDeSeguir (Integer idUsuario, Integer idUsuarioASeguir);




}
