package com.meli.itbootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoDTO {
    private PostDTO posts;
    private boolean hasPromo;
    private double discount;

}
