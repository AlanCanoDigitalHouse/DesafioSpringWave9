package com.example.socialmeli.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Validated
@Data
public class PostRequestDto {
    @NotNull(message = "userId null")
    @Min(1)
    private Integer userId;

    @NotNull(message = "date es requerido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    private ProductRequestDto detail;

    @NotNull(message = "category null")
    @Min(1)
    private Integer category;

    @DecimalMin("0.0")
    private Double price;

    private Boolean hasPromo;


    private Double discount;
}
