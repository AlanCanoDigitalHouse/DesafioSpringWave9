package com.squareMeter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AmbientMetersDTO {
    private String name;
    private double meters;
}
