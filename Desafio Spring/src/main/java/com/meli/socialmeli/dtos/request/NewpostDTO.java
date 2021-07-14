package com.meli.socialmeli.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.socialmeli.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Setter
@Getter
public class NewpostDTO {
    private int userId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Calendar date;
    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    @Override
    public String toString(){
        return "{\n" + "userId: " + this.userId +
                "\n" + "date: " + this.date +
                "\n" + "detail: " + this.detail.toString() +
                "\n" + "category: " + this.category +
                "\n" + "price: " + this.price +
                "\n" + "hasPromo: " + this.hasPromo +
                "\n" + "discount: " + this.discount +
                "\n}";
    }
}
