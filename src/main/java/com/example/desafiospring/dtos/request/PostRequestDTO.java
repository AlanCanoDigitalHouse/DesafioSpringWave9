package com.example.desafiospring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostRequestDTO {

    @Min(message = "userId debe ser mayor que 0", value = 0)
    private int userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    private ProductRequestDTO detail;

    @Min(message = "category debe ser mayor que 0", value = 1)
    private int category;

    @Min(message = "price debe ser mayor que 0", value = 1)
    private double price;

}
