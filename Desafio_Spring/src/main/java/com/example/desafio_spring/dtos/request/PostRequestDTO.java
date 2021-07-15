package com.example.desafio_spring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRequestDTO {
    private Integer userId;
    @PastOrPresent(message = "La fecha debe ser igual o menor a la actual")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    @NotNull(message = "los detalles no pueden ser null")
    private ProductRequestDTO detail;
    @Min(message = "El valor debe ser mayor a 0", value = 1)
    private Integer category;
    @Min(message = "El valor debe ser mayor a 0", value = 1)
    private Double price;


    public PostRequestDTO(Integer userId, String date, ProductRequestDTO detail, Integer category, Double price) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.userId = userId;
        this.date = format.parse(date);
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

}
