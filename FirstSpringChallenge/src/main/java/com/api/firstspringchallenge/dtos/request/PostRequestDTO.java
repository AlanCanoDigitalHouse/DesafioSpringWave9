package com.api.firstspringchallenge.dtos.request;

import com.api.firstspringchallenge.enumerates.Category;
import com.api.firstspringchallenge.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Validated
@Setter
@Getter
public class PostRequestDTO {

    @NotNull(message = "usedid cannot be null")
    private int userId;
    @NotNull(message = "date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    @NotNull(message = "detail cannot be null")
    private Product detail;
    @NotNull(message = "category cannot be null")
    private Category category;
    @NotNull(message = "price cannot be null")
    private double price;
}
