package com.example.socialmeli.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Validated
public class PromoPostRequestDto {
    @Min(1)
    private Integer userId;

    @NotNull(message = "date es requerido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    private ProductRequestDto detail;

    @Min(1)
    private Integer category;

    @DecimalMin("0.0")
    private Double price;

    @NotNull
    private Boolean hasPromo;

    @DecimalMin("0.0")
    private Double discount;
}
