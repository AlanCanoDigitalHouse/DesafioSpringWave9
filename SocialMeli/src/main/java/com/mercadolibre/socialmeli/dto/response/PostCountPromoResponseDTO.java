package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.entity.UserBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
public class PostCountPromoResponseDTO extends UserBase {
    int promoproducts_count;

    public PostCountPromoResponseDTO(int userId, String userName, int promoproducts_count){
        super(userId, userName);
        this.promoproducts_count = promoproducts_count;
    }
}
