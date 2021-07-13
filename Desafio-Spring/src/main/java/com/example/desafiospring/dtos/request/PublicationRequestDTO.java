package com.example.desafiospring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//agregar validaciones
public class PublicationRequestDTO {

    private Integer userId;

    @JsonFormat(pattern="dd-mm-yyyy")
    private Date date;
    private ProductDTO detail;
    private Integer category;
    private Double price;

}
