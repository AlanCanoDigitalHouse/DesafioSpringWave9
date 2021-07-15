package com.example.desafiospring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PublicationRequestDTO {
    @NotNull(message ="Value is require")
    private Integer userId;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message ="Date is require")
    private LocalDate date;

    private @Valid ProductDTO detail;
    @NotNull(message ="Value is require")
    private Integer category;
    @NotNull(message ="Value is require")
    private Double price;

}
