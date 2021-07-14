package com.mercadolibre.socialmeli.dto.respons;

import com.mercadolibre.socialmeli.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionCountResponse extends User {
    private int promoproduct_count;
}
