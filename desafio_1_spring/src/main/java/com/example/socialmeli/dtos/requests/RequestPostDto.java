package com.example.socialmeli.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Validated
public class RequestPostDto {
    @Min(message = "El user id debe ser un numero positivo", value=1)
    private Integer userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @Valid
    private RequestProductDto detail;
    @Min(message = "La categoría debe ser un numero positivo", value=1)
    private Integer category;
    @Min(message = "El precio debe ser un numero positivo", value=1)
    private double price;
}
