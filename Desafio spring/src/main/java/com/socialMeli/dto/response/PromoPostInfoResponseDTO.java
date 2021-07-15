package com.socialMeli.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PromoPostInfoResponseDTO extends PostInfoResponseDTO {
    private boolean hasPromo;
    private double discount;
}
