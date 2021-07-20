package com.example.socialmeli2.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Publicacion {

    private Integer publicacion_id;
    //private Integer userId;
    private Date fecha;
    private Integer product_id;
    private Integer category;
    private Double precio;

}
