package com.example.socialmeli2.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@Validated
public class PublicacionRequestDTO {

    @NotNull(message = "userId no puede ser nulo")
    private Integer userId;

    @NotNull(message = "date no puede ser nulo")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "detail no puede ser nulo")
    @Valid
    private ProductoRequestDTO detail;

    @NotNull(message = "categoria no puede ser nulo")
    private Integer category;

    @NotNull(message = "Precio no puede ser nulo")
    private Double precio;
}
