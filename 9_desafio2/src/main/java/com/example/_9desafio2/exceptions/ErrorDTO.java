package com.example._9desafio2.exceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {
        private final String name;
        private final String description;
}
