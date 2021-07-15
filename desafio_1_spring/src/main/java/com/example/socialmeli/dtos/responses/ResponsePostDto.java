package com.example.socialmeli.dtos.responses;

import com.example.socialmeli.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResponsePostDto {
    //Agregar el id_post... donde lo creo?
    private LocalDate date;
    private Product detail;
    private Integer category;
    private double price;
    private Boolean hasPromo;
    private double discount;
}
