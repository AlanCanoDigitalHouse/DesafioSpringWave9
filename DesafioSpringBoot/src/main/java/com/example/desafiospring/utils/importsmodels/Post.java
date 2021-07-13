package com.example.desafiospring.utils.importsmodels;

public class Post {
    private long userId;
    private String date;
    private long detail;
    private long category;
    private long price;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long value) {
        this.userId = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String value) {
        this.date = value;
    }

    public long getDetail() {
        return detail;
    }

    public void setDetail(long value) {
        this.detail = value;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long value) {
        this.category = value;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long value) {
        this.price = value;
    }
}
