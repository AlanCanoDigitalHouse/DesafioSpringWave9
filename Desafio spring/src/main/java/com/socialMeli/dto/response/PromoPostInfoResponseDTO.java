package com.socialMeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromoPostInfoResponseDTO extends PostInfoResponseDTO {
    private boolean hasPromo;
    private double discount;
}
