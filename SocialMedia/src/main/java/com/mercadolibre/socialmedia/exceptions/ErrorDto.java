package com.mercadolibre.socialmedia.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String name;
    private String description;

    public ErrorDto(String name, String description){
        this.name = name;
        this.description = description;
    }
}