package com.example.desafiospring.dtos.request;

import java.util.Date;
import java.util.List;

public class NewPostDto {
    private int userId;
    private String date;
    private List<NewPostDetailDto> detail;
    private int category;
    private double price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public List<NewPostDetailDto> getDetail() {
        return detail;
    }

    public void setDetail(List<NewPostDetailDto> detail) {
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

    public NewPostDto(int userId, String date, List<NewPostDetailDto> detail, int category, double price) {
        this.userId = userId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public NewPostDto() {
    }
}
