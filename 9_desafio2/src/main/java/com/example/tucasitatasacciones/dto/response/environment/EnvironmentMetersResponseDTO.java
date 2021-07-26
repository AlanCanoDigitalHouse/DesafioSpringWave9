package com.example.tucasitatasacciones.dto.response.environment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnvironmentMetersResponseDTO {
    private final String environment_name;
    private final String square_meters;
}
