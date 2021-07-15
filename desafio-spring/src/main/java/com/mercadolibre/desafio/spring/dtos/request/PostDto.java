package com.mercadolibre.desafio.spring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;
import java.util.Date;


@Data
@Validated
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PostDto {

    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ProductDto detail;
    private Integer category;
    private Double price;

}
