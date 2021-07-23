package com.squareMeter.exception.model;

import lombok.*;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private final String name;
    private final String description;
}
