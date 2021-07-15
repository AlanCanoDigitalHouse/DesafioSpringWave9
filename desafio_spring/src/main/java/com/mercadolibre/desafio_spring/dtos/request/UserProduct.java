package com.mercadolibre.desafio_spring.dtos.request;

import com.mercadolibre.desafio_spring.entities.Product;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class UserProduct {

    @Min(message = "Id errado",value = 1)
    Integer userId;

    @Min(message = "Id errado",value = 1)
    Integer id_post;

    Date date;

    @Valid
    Product detail;

    @Min(message = "Id errado",value = 1)
    Integer category;

    @Min(message = "Id errado",value = 0)
    Double price;

    public UserProduct(int userId, int id_post, Date date, Product detail, int category, double price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
