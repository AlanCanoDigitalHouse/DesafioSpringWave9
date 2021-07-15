package com.mercadolibre.desafio_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donation {
    User donor;
    Double donation;
    Integer post_id;
}
