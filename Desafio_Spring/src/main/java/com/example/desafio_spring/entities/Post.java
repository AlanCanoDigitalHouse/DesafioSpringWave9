package com.example.desafio_spring.entities;

import com.example.desafio_spring.dtos.request.ProductRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
@Data
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private Integer userId;
    private Integer id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ArrayList<ProductRequestDTO> detail;
    private Integer category;
    private Double price;

    @AssertTrue(message = "El valor debe ser true")
    private boolean hasPromo;
    private Double discount;



    public Post(Integer userId, Integer id_post, Date date, ArrayList<ProductRequestDTO> detail, Integer category, Double price) throws ParseException {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public Post(Integer userId, Integer id_post, Date date, ArrayList<ProductRequestDTO> detail, Integer category, Double price, boolean hasPromo, Double discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
    /*
    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.date = format.parse(date);
    }*/
}
