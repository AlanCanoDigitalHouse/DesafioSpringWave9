package com.example.socialmeli2.ModelosDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDto {
    private int id;
    private String nombre;
    private String rol;
    private int seguidores;
    private List<DatosUsuariosDto> listaSeguidores;
    private int seguidos;
    private List<DatosUsuariosDto> listaSeguidos;
}

