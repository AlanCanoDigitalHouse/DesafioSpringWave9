package com.example.desafiotesting.service;

import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.EnvironmentResponseDTO;
import com.example.desafiotesting.dto.response.ResponseDTO;
import com.example.desafiotesting.exception.DistrictNotFoundException;
import com.example.desafiotesting.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PropertyService {

    DistrictRepository districtRepository;

    public PropertyService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public ResponseDTO calculateAll(PropertyDTO property) throws DistrictNotFoundException {
        districtRepository.propertyExists(property.getDistrict().getDistrict_name());
        Double propertySize = this.calculatePropertySize(property.getEnvironments());
        Double propertyPrice = propertySize * property.getDistrict().getDistrict_price();
        EnvironmentResponseDTO biggerEnvironment = this.calculateBiggerEnvironment(property.getEnvironments());
        List<EnvironmentResponseDTO> environmentResponseDTO = this.calculateEachSizeEnvironment(property.getEnvironments());
        return new ResponseDTO(propertySize, propertyPrice, biggerEnvironment, environmentResponseDTO);
    }

    public Double calculatePropertySize(List<EnvironmentDTO> environments){
        Double totalSize = 0.0;
        for (EnvironmentDTO environment:
             environments) {
            totalSize += environment.calculateSize();
        }
        return totalSize;
    }

    public EnvironmentResponseDTO calculateBiggerEnvironment(List<EnvironmentDTO> environments) {
        EnvironmentDTO biggerEnvironment = environments
                .stream().max(Comparator.comparing(EnvironmentDTO::calculateSize)).get();
        return new EnvironmentResponseDTO(
                biggerEnvironment.getEnvironment_name(),
                biggerEnvironment.calculateSize()
        );
    }

    public List<EnvironmentResponseDTO> calculateEachSizeEnvironment(List<EnvironmentDTO> environments) {
        List<EnvironmentResponseDTO> environmentsSize = new ArrayList<>();
        for (EnvironmentDTO environment:
                environments) {
            environmentsSize.add(
                    new EnvironmentResponseDTO(
                            environment.getEnvironment_name(),
                            environment.calculateSize()
                    )
            );
        }
        return environmentsSize;
    }
}
