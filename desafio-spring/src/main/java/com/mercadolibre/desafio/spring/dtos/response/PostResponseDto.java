package com.mercadolibre.desafio.spring.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.desafio.spring.dtos.request.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


/**
 * Entity to generate the response with the data requested in the request.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public class PostResponseDto {
    private Integer userId;
    private ArrayList<PostDto> posts= new ArrayList<>();
}
