package com.mercadolibre.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Publication {
    @NotNull(message = "the user field must not be null")
    @Min(value = 1, message = "the userId field must not be 0")
    private int userId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "the date field must not be null")
    private Calendar date;
    @Valid
    @NotNull(message = "the detail object must not be null")
    private Product detail;
    @NotNull(message = "the category field must not be null")
    @Min(value = 1, message = "the category field must not be 0")
    private int category;
    @NotNull(message = "the price field must not be null")
    private double price;
    private boolean hasPromo;
    private double discount;

}
