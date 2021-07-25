package com.desafio.tucasitatasaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDTO extends PropertyRequestDTO{
    private String biggest_environment;
    private double prop_area;
    private double prop_price;

    public PropertyResponseDTO(PropertyRequestDTO property, double prop_area, double prop_price, String biggest_enviroment){
        super(property);
        this.biggest_environment = biggest_enviroment;
        this.prop_area = prop_area;
        this.prop_price = prop_price;
    }
}
