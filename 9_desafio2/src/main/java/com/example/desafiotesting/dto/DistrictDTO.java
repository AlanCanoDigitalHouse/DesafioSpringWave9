package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class DistrictDTO {
    @Length(max = 45, message = "District name can't be longer than 45")
    @NotEmpty(message = "District name can't be empty")
    @NotNull(message = "District name can't be null")
    private String district_name;
    @DecimalMin(value = "0.0", message = "District price can't be a negative number")
    @NotNull(message = "District price can't be null")
    @DecimalMax(value = "4000.00", message = "District price can't be higher than 4500.00 U$S")
    private Double district_price;
}
