package com.mercadolibre.desafio.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
public class PublicsFollowedDTO {
    private Integer userId;
    private List<PublicsResponseDTO> posts;
}
