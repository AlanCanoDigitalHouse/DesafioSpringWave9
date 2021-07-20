package com.example.socialmeli2.Servicios;

import com.example.socialmeli2.Modelos.Usuario;
import com.example.socialmeli2.dtos.responses.ListadoPublicacionesResponseDTO;

import java.util.List;

public interface IServicioPublicacion {
    List<Usuario> getDataBase();

    ListadoPublicacionesResponseDTO listadoPublicacionePorVendedor (Integer idUsuario );
}
