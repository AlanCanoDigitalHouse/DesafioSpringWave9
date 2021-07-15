package com.mercadolibre.desafio.demo.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafio.demo.validators.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NewPostDTO {

    @NotNull(message = "The parameter 'userId' can not be null")
    private Integer userId;


    @NotNull(message = "The parameter 'date' can not be null")
    @ValidDate(message = "the date entered cannot be greater than today")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date date;

    @NotNull(message = "The parameter 'detail' can not be null")
    @Valid
    private DetailRequestDTO detail;

    @NotNull(message = "The parameter 'category' can not be null")
    private Integer category;

    @NotNull(message = "The parameter 'price' can not be null")
    @DecimalMin(value = "0.0", message = "")
    @Digits(integer = 10000,fraction = 2, message = "Limit decimal digits to 2")
    private Double price;

}
