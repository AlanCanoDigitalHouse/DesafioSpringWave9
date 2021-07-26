package com.example.tucasitatasacciones.dto.response.environment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentResponseDTO {
    private String environment_name;
    private Double environment_width;
    private Double environment_length;
}
