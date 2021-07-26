package com.bootcamp.desafio2.dtos.response;

import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDto {

    private Double totalArea;
    private Double propertyPrice;
    private String biggerEnvironment;
    private List<EnvironmentDto> environments;
}
