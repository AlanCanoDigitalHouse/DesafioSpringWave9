package com.meli.itbootcamp.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private  static  AtomicInteger idProductGenerator= new AtomicInteger();
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public Product() {
        this.product_id = idProductGenerator.incrementAndGet();
    }

    public Product(String productName, String type, String brand, String color, String notes) {
        this.product_id = idProductGenerator.incrementAndGet();
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public Product(Integer product_id, String productName, String type, String brand, String color, String notes) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Product){
            return  this.equals(((Product) obj).getProduct_id());
        }
        return false;
    }

}

