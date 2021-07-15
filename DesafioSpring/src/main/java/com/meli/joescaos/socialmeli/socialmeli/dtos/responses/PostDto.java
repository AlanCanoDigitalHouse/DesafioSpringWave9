package com.meli.joescaos.socialmeli.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.joescaos.socialmeli.socialmeli.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private Product detail;
    private int category;
    private double price;
}
