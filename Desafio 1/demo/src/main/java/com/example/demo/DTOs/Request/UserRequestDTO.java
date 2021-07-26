package com.example.demo.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Valor numerico erroneo")
    @Min(value = 1, message = "Usuario no existente")
    @Max(value = 10, message = "Usuario no existente")
    @NotNull(message = "No puede ser nulo el valor del usuario")
    Integer userId;
}
