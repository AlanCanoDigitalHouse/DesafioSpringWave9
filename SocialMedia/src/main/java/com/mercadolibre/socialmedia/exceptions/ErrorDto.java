package com.mercadolibre.socialmedia.exceptions;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ErrorDto {
    private String name;
    private String description;

    public ErrorDto(String name, String description){
        this.name = name;
        this.description = description;
    }
}