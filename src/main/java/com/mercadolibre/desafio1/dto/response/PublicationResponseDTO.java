package com.mercadolibre.desafio1.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.time.LocalDate;

@Valid
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationResponseDTO {
    private Integer id_post;
    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductResponseDTO detail;
    private Integer category;
    private Double price;

    public PublicationResponseDTO(PublicationResponseDTO publicationResponseDTO) {
        this.id_post = publicationResponseDTO.getId_post();
        this.userId = publicationResponseDTO.getUserId();
        this.date = publicationResponseDTO.getDate();
        this.detail = publicationResponseDTO.getDetail();
        this.category = publicationResponseDTO.getCategory();
        this.price = publicationResponseDTO.getPrice();
    }
}