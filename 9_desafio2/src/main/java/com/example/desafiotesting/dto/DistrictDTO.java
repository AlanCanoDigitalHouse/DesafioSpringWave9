package com.example.desafiotesting.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DistrictDTO {

    @Size(max = 45)
    @NotBlank
    @NotNull
    String district_name;

    @NotNull
    @DecimalMax(value = "4000")
    Double district_price;
}
