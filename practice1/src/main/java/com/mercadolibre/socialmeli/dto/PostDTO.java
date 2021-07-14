package com.mercadolibre.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostDTO {
    // User FK
    @NotNull
    private Integer userId;
    // PK
    private Integer id_post;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    Date date;
    @NotNull
    private ProductDTO detail;
    // Category FK
    @NotNull
    private Integer category;
    @Min(value = 0)
    @NotNull
    private double price;

}
