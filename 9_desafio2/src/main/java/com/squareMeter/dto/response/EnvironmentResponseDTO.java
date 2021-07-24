package com.squareMeter.dto.response;

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
