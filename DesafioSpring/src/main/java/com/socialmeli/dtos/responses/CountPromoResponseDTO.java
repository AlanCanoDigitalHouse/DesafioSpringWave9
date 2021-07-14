package com.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountPromoResponseDTO {

    private Integer userId;
    private String userName;

    @JsonProperty(value = "promoproducts_count")
    private Integer countPromo;
}
