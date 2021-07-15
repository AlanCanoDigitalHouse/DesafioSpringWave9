package com.mercadolibre.socialmeli.dtos.Product.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.socialmeli.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostDTO {

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
    @NotNull(message = "El precio debe estar")
    private Double price;

}
