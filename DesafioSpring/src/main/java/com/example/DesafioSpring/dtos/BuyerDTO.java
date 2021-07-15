package com.example.DesafioSpring.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
@Data

public class BuyerDTO {

    @Min(message = "User ID must be greater than 0", value = 0)
    private Integer userid;

    @Min(message = "Seller ID must be greater than 0", value = 0)
    private Integer sellerid;

}
