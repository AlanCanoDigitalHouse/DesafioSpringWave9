package com.socialMeli.model;

import com.socialMeli.exception.exception.DateNotValidException;
import com.socialMeli.utils.DateValidatorDateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostBuilder {
    private final int id;
    private int userId;
    private Date date;
    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
    private int category;
    private double price;

    public PostBuilder(int id) {
        this.id = id;
    }

    public PostBuilder setDate(String date) throws DateNotValidException, ParseException {
        Date today = new Date();
        if(!DateValidatorDateTimeFormatter.isValid(date)) throw new DateNotValidException(date);
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if(today.compareTo(format.parse(date)) < 0) throw new DateNotValidException(date, "That date is in the  future!");
        this.date = format.parse(date);
        return this;
    }

    public PostBuilder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public PostBuilder setProduct_id(int product_id) {
        this.product_id = product_id;
        return this;
    }

    public PostBuilder setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public PostBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public PostBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public PostBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public PostBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public PostBuilder setCategory(int category) {
        this.category = category;
        return this;
    }

    public PostBuilder setPrice(double price) {
        this.price = price;
        return this;
    }



    public PostModel build(){
        return new PostModel(id,userId,date,product_id,productName,type,brand,color,notes,category,price);
    }
}
