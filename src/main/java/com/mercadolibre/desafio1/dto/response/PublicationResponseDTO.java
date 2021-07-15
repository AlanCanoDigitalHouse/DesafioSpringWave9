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
    private Boolean hasPromo;
    private Double discount;

    public PublicationResponseDTO(PublicationResponseDTO publication) {
        this.id_post = publication.id_post;
        this.userId = publication.userId;
        this.date = publication.date;
        this.detail = publication.detail;
        this.category = publication.category;
        this.price = publication.price;
        this.hasPromo = publication.hasPromo;
        this.discount = publication.discount;
    }
}