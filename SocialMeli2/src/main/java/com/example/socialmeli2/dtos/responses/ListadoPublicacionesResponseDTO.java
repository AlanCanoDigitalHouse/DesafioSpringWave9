package com.example.socialmeli2.dtos.responses;

import com.example.socialmeli2.Modelos.DatosUsuarios;
import com.example.socialmeli2.Modelos.Publicacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListadoPublicacionesResponseDTO {
    private Integer userId;
    private List<Publicacion> posts ;

}

