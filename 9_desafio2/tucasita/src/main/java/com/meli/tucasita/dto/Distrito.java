package com.meli.tucasita.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Distrito {
    private String districtName;
    private Double pricePerMeter;
}
