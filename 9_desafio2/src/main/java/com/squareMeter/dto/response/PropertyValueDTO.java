package com.squareMeter.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertyValueDTO {
    private final double value;
}
