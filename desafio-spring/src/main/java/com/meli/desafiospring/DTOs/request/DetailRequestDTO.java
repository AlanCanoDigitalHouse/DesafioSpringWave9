package com.meli.desafiospring.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DetailRequestDTO {

    @Size(max = 30)
    @NotNull
    String productName;
    @Size(max = 30)
    @NotNull
    String type;
    @Size(max = 30)
    @NotNull
    String brand;
    @Size(max = 20)
    @NotNull
    String color;
    @Size(max = 150)
    @NotNull
    String notes;

}
