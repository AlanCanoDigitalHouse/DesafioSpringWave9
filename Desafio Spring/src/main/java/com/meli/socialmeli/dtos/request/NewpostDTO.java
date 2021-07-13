package com.meli.socialmeli.dtos.request;

import com.meli.socialmeli.models.Product;

import java.util.Optional;

public class NewpostDTO {
    private int userId;
    private String date;
    private Product detail;
    private int category;
    private double price;
    private Optional<Boolean> hasPromo;
    private Optional<Double> discount;
}
