package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {


    @Min(message = "need positive number, min required 0", value = 0)
    @NotNull(message = "UserId is mandatory")
    private Integer userId;

    private Integer postId;

    @NotNull(message = "Date is mandatory")
    @PastOrPresent(message = "Not a valid Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    private Product detail;

    @Min(message = "need positive number, min required 0", value = 0)
    @NotNull(message = "Category is mandatory")
    private Integer category;

    @DecimalMin("0.0")
    @NotNull(message = "Price is mandatory")
    private Double price;

    private Boolean hasPromo;

    @DecimalMin("0.0")
    private Double discount;



}
