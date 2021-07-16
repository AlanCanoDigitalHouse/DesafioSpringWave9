package com.example.socialmeli2.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private int id;
    private String nombre;
    private String rol;
    private int seguidores;
    private List<DatosUsuarios> listaSeguidores;
    private int seguidos;
    private List<DatosUsuarios> listaSeguidos;
    private List<Publicacion> listaPublicaciones;
}

