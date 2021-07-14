package com.mercadolibre.socialmeli.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Validated
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {

    @NotNull(message = "userId cannot be empty")
    private Integer userId;
    private Integer id_post;
    @NotNull(message = "date cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    @NotNull(message = "detail cannot be empty")
    @Valid
    private ProductDTO detail;
    @NotNull(message = "category cannot be empty")
    private Integer category;
    @NotNull(message = "price cannot be empty")
    private Double price;

}
