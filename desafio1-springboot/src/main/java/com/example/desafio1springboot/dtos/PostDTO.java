package com.example.desafio1springboot.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Validated
public class PostDTO {

    static int instanceCounter = 0;

    @Min(value = 1, message = "El valor debe ser como minimo 1")
    private Integer userId;

    private Integer id_post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "El atributo no puede ser nulo")
    private Date date;

    @Valid
    private ProductDTO detail;

    @Min(value = 1, message = "El atributo debe ser mayor a 1")
    @NotNull(message = "El atributo no puede ser nulo")
    private Integer category;

    @NotNull(message = "El atributo no puede ser nulo")
    private Double price;

    public PostDTO() {
        instanceCounter++;
        this.id_post = instanceCounter;
    }
}