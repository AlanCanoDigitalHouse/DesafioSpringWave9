package com.example.demo.dtos.generals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    private String district_name;
    private Double district_price;

}
