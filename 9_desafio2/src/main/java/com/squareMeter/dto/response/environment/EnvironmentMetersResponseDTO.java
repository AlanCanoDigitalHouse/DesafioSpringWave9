package com.squareMeter.dto.response.environment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnvironmentMetersResponseDTO {
    private final String environment_name;
    private final String square_meters;
}
