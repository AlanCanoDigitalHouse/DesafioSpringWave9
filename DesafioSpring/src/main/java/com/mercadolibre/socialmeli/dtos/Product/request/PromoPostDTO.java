package com.mercadolibre.socialmeli.dtos.Product.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mercadolibre.socialmeli.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PromoPostDTO {

    @NotNull(message = "El id del usuario debe estar")
    private Integer userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "La fecha debe estar")
    private Date date;

    @Valid
    @NotNull(message = "El detalle debe estar")
    private Product detail;
    @NotNull(message = "La categoría debe estar")
    private Integer category;
    @Min(1)
    @NotNull(message = "El precio debe estar")
    private Double price;
    @NotNull(message = "Debe colocarla en true")
    private Boolean hasPromo;
    @NotNull(message = "Debe contener un descuento")
    @Min(0)
    private Double discount;
}
