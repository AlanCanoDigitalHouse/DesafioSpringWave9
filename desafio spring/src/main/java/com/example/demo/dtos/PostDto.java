package com.example.demo.dtos;

import com.example.demo.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class PostDto {

    private int userId;
    private int id_post;

    @JsonFormat(pattern = "dd-MM-yyyy")
    //@DateTimeFormat(pattern="dd-MM-yyyy")
    private Date date;

    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public int checkIfPostIsPromo() {
        if (this.hasPromo){
            return 1;
        }
        return 0;
    }
}
