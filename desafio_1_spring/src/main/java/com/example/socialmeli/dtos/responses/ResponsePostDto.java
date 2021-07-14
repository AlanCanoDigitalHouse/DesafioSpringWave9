package com.example.socialmeli.dtos.responses;

import com.example.socialmeli.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResponsePostDto {
    //Agregar el id_post... donde lo creo?
    private Date date;
    private Product detail;
    private Integer category;
    private double price;
}
