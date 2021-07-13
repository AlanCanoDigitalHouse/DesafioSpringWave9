package com.jbianchini.meli.socialmeli.dto.request;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class NewPostRequest {
    private int userId;
    private LocalDate date;
    private List<ProductRequest> detail;
    private int category;

    private double price;
}
