package com.example.demo.dtos;

import com.example.demo.models.ProductDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PostDTO {

    @JsonProperty("id_post")
    private Integer idPost;

    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductDetail detail;
    private Integer category;
    private Double price;

    public PostDTO(Integer userId, LocalDate date, ProductDetail detail, Integer category, Double price) {
        this.userId = userId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public PostDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public void setDetail(ProductDetail detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "userId=" + userId +
                ", date=" + date +
                ", idPost=" + idPost +
                ", detail=" + detail +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
