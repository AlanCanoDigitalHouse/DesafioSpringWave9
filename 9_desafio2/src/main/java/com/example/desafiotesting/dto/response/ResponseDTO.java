package com.example.desafiotesting.dto.response;

import com.example.desafiotesting.dto.EnvironmentDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ResponseDTO {
    Double propertySize;

    Double propertyPrice;

    EnvironmentResponseDTO biggerEnvironment;

    List<EnvironmentResponseDTO> environments;
}
