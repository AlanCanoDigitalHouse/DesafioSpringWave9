package com.example.demo.Services;

import com.example.demo.DTOs.DistrictDTO;
import com.example.demo.DTOs.EnvironmentResponseDTO;
import com.example.demo.DTOs.PropertyDTO;
import com.example.demo.DTOs.PropertyDetailsDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.District;
import com.example.demo.Models.Environment;
import com.example.demo.Models.Property;
import com.example.demo.Repositories.IDistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IDistrictRepository repository;

    @Override
    public PropertyDetailsDTO getDetails(PropertyDTO propertyDTO) throws CustomExceptionHandler {
        Property prop = mapper.map(propertyDTO, Property.class);
        District district = repository.findDistrictByName(prop.getDistrict_name());
        if (district == null) {
            throw new NotFoundException(prop.getDistrict_name());
        }
        return new PropertyDetailsDTO(propertyDTO.getProp_name(),
                calculatePropertySurface(prop.getEnvironments()),
                calculatePrice(prop, district.getDistrict_price()),
                findBiggestEnvironment(prop.getEnvironments()),
                generateEnvironmentsCalculations(prop.getEnvironments()));
    }

    @Override
    public String addDistrict(DistrictDTO districtDto) throws CustomExceptionHandler {
        District district = mapper.map(districtDto, District.class);
        repository.addDistrict(district);
        return "District was added successfully";
    }

    private double calculateEnvironmentSurface(Environment environment) {
        return environment.getEnvironment_length() * environment.getEnvironment_width();
    }

    private EnvironmentResponseDTO generateResponseDto(Environment environment) {
        return new EnvironmentResponseDTO(environment.getEnvironment_name(), calculateEnvironmentSurface(environment));
    }

    private double calculatePropertySurface(List<Environment> environments) {
        return environments.stream()
                .mapToDouble(e -> calculateEnvironmentSurface(e))
                .sum();
    }

    private double calculatePrice(Property property, Double price){
        return calculatePropertySurface(property.getEnvironments()) * price;
    }

    private List<EnvironmentResponseDTO> generateEnvironmentsCalculations(List<Environment> environments){
        return environments.stream()
                .map(e -> generateResponseDto(e))
                .collect(Collectors.toList());
    }

    private EnvironmentResponseDTO findBiggestEnvironment(List<Environment> environments){
        Environment maxEnvironment;
        try{
            maxEnvironment = environments
                    .stream()
                    .max(Comparator.comparing(e -> calculateEnvironmentSurface(e)))
                    .orElseThrow(NoSuchElementException::new);
        } catch(Exception e) {
            return null;
        }
        return generateResponseDto(maxEnvironment);
    }

}
