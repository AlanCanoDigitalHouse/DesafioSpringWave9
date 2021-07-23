package com.mercadolibre.tucasitatasaciones.service;

import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.*;
import com.mercadolibre.tucasitatasaciones.exception.InvalidRequestData;
import com.mercadolibre.tucasitatasaciones.repository.IDistrictRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {

    private final IDistrictRepository districtRepository;

    public PropertyService(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public PropertyTotalAreaDTO calculatePropertyTotalArea(PropertyRequestDTO propData) {
        var totalArea = this.calculateEnvironmentsArea(propData)
                .getEnvironments()
                .stream().reduce(0D, (acc, curr) -> acc + curr.getArea(), Double::sum);

        return new PropertyTotalAreaDTO(propData.getName(), totalArea);
    }

    @Override
    public PropertyValuationDTO valuateProperty(PropertyRequestDTO propData) {
        this.validateDistrict(propData.getDistrict());

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

    private void validateDistrict(DistrictDTO districtData) {
        var district = this.districtRepository.getDistrictByName(districtData.getName());

        if (!district.getPrice().equals(districtData.getPrice())) {
            throw new InvalidRequestData("Provided district data does not match the records");
        }
    }

}
