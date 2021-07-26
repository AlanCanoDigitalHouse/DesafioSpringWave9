package com.apismeli.socialmeli.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Validated
public class PublicationDTO {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer id_post;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull
    private ProductDTO detail;


    @NotNull
    private Integer category;

    @NotNull
    @Min(value = 0)
    private Double price;
}
