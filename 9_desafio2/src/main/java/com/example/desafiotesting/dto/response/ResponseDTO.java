package com.example.desafiotesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ResponseDTO {
    Double propertySize;

    Double propertyPrice;

    EnvironmentResponseDTO biggerEnvironment;

    List<EnvironmentResponseDTO> environments;
}
