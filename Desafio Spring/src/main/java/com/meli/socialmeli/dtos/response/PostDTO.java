package com.meli.socialmeli.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.socialmeli.dtos.request.NewpostDTO;
import com.meli.socialmeli.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Setter
@Getter
public class PostDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Calendar date;
    private Product detail;
    private int category;
    private double price;

    public PostDTO(NewpostDTO post){
        this.date = post.getDate();
        this.detail = post.getDetail();
        this.category = post.getCategory();
        this.price = post.getPrice();
    }

    @Override
    public String toString(){
        return "{\n" + "date: " + this.date +
                "\n" + "detail: " + this.detail.toString() +
                "\n" + "category: " + this.category +
                "\n" + "price: " + this.price +
                "\n}";
    }
}
