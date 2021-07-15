package com.example.demo.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Validated
public class Post {

    @NotNull(message = "UserId is mandatory")
    @NotBlank(message = "UserId can't be empty")
    private Integer userId;
    private Integer id_post;

    @JsonFormat(pattern="dd-MM-yyyy")
    @FutureOrPresent(message = "Post date can't be in the future")
    private LocalDate date;

    private @Valid Product detail;

    @Min(value = 1, message = "Category must be greater than 0")
    private Integer category;

    @DecimalMin(value = "1.0", message = "Price can't be less than 1.0")
    private Double price;

}
