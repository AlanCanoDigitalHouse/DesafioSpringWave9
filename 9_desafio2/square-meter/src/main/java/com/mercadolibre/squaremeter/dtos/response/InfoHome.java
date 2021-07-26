package com.mercadolibre.squaremeter.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoHome {
    private Double squareMeter;
    private Double priceAppraisal;
    private String environmentBiggest;
    private List<InfoEnvironments> environmentsMeter;

}
