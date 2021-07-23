package com.meli.socialmeli.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.socialmeli.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Calendar;

@Setter
@Getter
@Validated
public class NewpostDTO {
    @Positive(message = "Must be greater than 0.")
    private int userId;
    @FutureOrPresent(message = "Must be a date on the future or present.") //Can schedule a post
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private Calendar date;
    @Valid
    private Product detail;
    private int category;
    @Positive(message = "Must be greater than 0.")
    private double price;

    @Override
    public String toString(){
        return "{\n" + "userId: " + this.userId +
                "\n" + "date: " + this.date +
                "\n" + "detail: " + this.detail.toString() +
                "\n" + "category: " + this.category +
                "\n" + "price: " + this.price +
                "\n}";
    }
}
