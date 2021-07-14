package com.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {

    @JsonProperty("id_post")
    private Integer idPost;
    private String date;
    private ProductResponseDTO detail;
    private Integer category;
    private Double price;
}
