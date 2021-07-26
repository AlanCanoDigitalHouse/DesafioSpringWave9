package com.example.desafio_spring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class PostPromoRequestDTO {
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
    @AssertTrue(message = "El valor debe ser true")
    @NotBlank(message = "El atributo no puede ser nulo")
    private boolean hasPromo;
    private Double discount;

    public PostPromoRequestDTO(Integer userId, String date, ProductRequestDTO detail, Integer category, Double price, boolean hasPromo, Double discount) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.userId = userId;
        this.date = format.parse(date);
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
