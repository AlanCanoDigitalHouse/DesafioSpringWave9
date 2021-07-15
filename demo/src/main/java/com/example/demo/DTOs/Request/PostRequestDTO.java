package com.example.demo.DTOs.Request;

import com.example.demo.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {

    @NotNull(message = "No debe ser nulo")
    private int userId;

    @NotNull(message = "No debe ser nulo")
    private Product detail;

    @NotNull(message = "No debe ser nulo")
    private double price;

    @NotNull(message = "No debe ser nulo")
    private int category;

}
