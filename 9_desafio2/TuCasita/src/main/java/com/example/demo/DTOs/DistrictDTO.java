package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {

    @NotNull(message = "District field can not be empty.")
    @Size(min = 1, message = "The district field can not be empty.")
    @Size(max = 45, message = "Length of district cannot exceed 45 characters.")
    private String district_name;

    @NotNull(message = "District price field can not be empty.")
    @DecimalMax(value="4000.0", message = "The maximum price allowed per square meter is 4000 USD.")
    @DecimalMin(value="1.0", message = "The minimum price allowed per square meter is 1 USD.")
    private Double district_price;

}
