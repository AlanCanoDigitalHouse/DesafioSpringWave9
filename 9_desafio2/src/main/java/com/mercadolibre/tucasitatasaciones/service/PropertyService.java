package com.mercadolibre.tucasitatasaciones.service;

import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {

    @Override
    public PropertyTotalAreaDTO calculatePropertyTotalArea(PropertyRequestDTO propData) {
        var totalArea = this.calculateEnvironmentsArea(propData)
                .getEnvironments()
                .stream().reduce(0D, (acc, curr) -> acc + curr.getArea(), Double::sum);

        return new PropertyTotalAreaDTO(propData.getName(), totalArea);
    }

    @Override
    public PropertyValuationDTO valuateProperty(PropertyRequestDTO propData) {
        var totalArea = this.calculatePropertyTotalArea(propData).getArea();
        var price = totalArea * propData.getDistrict().getPrice();

        return new PropertyValuationDTO(propData.getName(), price);
    }

    @Override
    public LargestEnvironmentDTO determineLargestEnvironment(PropertyRequestDTO propData) {
        var maxArea = this.calculateEnvironmentsArea(propData)
                .getEnvironments()
                .stream().max(Comparator.comparing(EnvironmentAreaDTO::getArea))
                .orElse(null);

        return new LargestEnvironmentDTO(propData.getName(), maxArea);
    }

    @Override
    public PropertyEnvironmentsAreaDTO calculateEnvironmentsArea(PropertyRequestDTO propData) {
        var environmentsWithArea = propData.getEnvironments()
                .stream().map(e -> new EnvironmentAreaDTO(e.getName(), this.calculateArea(e.getWidth(), e.getLength())))
                .collect(Collectors.toList());

        return new PropertyEnvironmentsAreaDTO(propData.getName(), environmentsWithArea);
    }

    private Double calculateArea(Double width, Double length) {
        return width * length;
    }

}
