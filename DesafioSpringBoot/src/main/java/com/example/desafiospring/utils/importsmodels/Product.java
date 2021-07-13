package com.example.desafiospring.utils.importsmodels;

public class Product {
    private long productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long value) {
        this.productId = value;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String value) {
        this.productName = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String value) {
        this.brand = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String value) {
        this.color = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String value) {
        this.notes = value;
    }
}
