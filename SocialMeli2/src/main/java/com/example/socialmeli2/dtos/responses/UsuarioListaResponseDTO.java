package com.example.socialmeli2.dtos.responses;

import com.example.socialmeli2.Modelos.DatosUsuarios;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioListaResponseDTO {
    private Integer userId;
    private String userName;
    private List<DatosUsuarios> followers;
}
