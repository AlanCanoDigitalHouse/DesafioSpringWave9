package com.mercadolibre.socialmeli.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PromoProductsCountDTO extends UserDTO {

    private Integer promoproducts_count;

}
