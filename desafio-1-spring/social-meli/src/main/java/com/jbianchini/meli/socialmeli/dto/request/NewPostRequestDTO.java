package com.jbianchini.meli.socialmeli.dto.request;

import java.time.LocalDate;
import java.util.List;

public class NewPostRequestDTO {
    private int userId;
    private LocalDate date;
    private List<ProductRequestDTO> detail;
    private int category;

    private double price;
}
