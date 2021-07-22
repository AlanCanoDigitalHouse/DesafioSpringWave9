package com.mercadolibre.desafio1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPromoListResponseDTO {
    private Integer userId;
    private String userName;
    private ArrayList<PublicationResponseDTO> posts;
}
