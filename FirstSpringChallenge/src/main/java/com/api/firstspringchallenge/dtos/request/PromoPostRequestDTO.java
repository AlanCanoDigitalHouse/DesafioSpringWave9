package com.api.firstspringchallenge.dtos.request;

import com.api.firstspringchallenge.enumerates.Category;
import com.api.firstspringchallenge.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Validated
@Setter
@Getter
public class PromoPostRequestDTO {

    @NotNull(message = "usedid cannot be null")
    private int userId;
    @NotNull(message = "date cannot be null")
    private Date date;
    @NotNull(message = "detail cannot be null")
    private Product detail;
    @NotNull(message = "category cannot be null")
    private Category category;
    @NotNull(message = "price cannot be null")
    private double price;
    @NotNull(message = "hasPromo cannot be null")
    private boolean hasPromo;
    @NotNull(message = "discount cannot be null")
    @Min(message = "discount cannot be negative", value = 0)
    private double discount;

    public PromoPostRequestDTO(int userId, String date, Category category, double price, Product detail, boolean hasPromo, double discount) throws ParseException {
        this.userId = userId;
        this.date = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
