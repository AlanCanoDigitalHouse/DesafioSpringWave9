package com.example.socialmeli.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProductRequestDTO {

    @NotNull(message = "EL nombre del producto no puede ser nulo")
    @NotBlank(message = "El nombre del producto no puede ser vacio")
    @Size(min = 2, max = 2000, message = "El nombre del producto debe contener entre 2 y 2000 caracteres")
    private String productName;

    @NotNull(message = "EL tipo de producto no puede ser nulo")
    @NotBlank(message = "El tipo de producto no puede ser vacio")
    @Size(min = 2, max = 2000, message = "El tipo de producto debe contener entre 2 y 2000 caracteres")
    private String type;

    @NotNull(message = "La marca del producto no puede ser nula")
    @NotBlank(message = "La marca del producto no puede ser vacia")
    @Size(min = 2, max = 2000, message = "La marca del producto debe contener entre 2 y 2000 caracteres")
    private String brand;

    @NotNull(message = "EL color del producto no puede ser nulo")
    @NotBlank(message = "El color del producto no puede ser vacio")
    @Size(min = 2, max = 2000, message = "El color del producto debe contener entre 2 y 2000 caracteres")
    private String color;


    private String notes;

}
