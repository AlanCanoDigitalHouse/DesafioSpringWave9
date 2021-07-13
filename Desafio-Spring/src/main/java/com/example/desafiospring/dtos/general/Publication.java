package com.example.desafiospring.dtos.general;

import com.example.desafiospring.dtos.request.ProductDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Publication {

    private Integer postId;
    @JsonFormat(pattern="dd-mm-yyyy")
    private Date date;
    private Product detail;
    private Integer category;
    private Double price;

}
