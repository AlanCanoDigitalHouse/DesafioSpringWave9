package com.example.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseValueResponseDto {

    private String name;
    private Double value;
}
