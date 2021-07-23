package com.squareMeter.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictModel {
    private String district_name;
    private Double district_price;
}
