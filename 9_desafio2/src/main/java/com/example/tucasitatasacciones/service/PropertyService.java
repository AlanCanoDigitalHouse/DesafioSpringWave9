package com.example.tucasitatasacciones.service;

import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentMetersResponseDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertySquareMetersResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertyValueDTO;
import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;
import com.example.tucasitatasacciones.model.EnvironmentModel;
import com.example.tucasitatasacciones.model.PropertyModel;
import com.example.tucasitatasacciones.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PropertyService implements IPropertyService {


    final private Mapper mapper;
    final private IDistrictService districtService;


    @Override
    public PropertySquareMetersResponseDTO getHouseTotalSquareMeters(PropertyRequestDTO propertyRequestDTO) {
        PropertyModel propertyModel = mapper.map(propertyRequestDTO, PropertyModel.class);
        double total = propertyModel.getEnvironments().stream().mapToDouble(room -> room.getEnvironment_width() * room.getEnvironment_length()).sum();
        return new PropertySquareMetersResponseDTO(total);
    }

    @Override
    public PropertyValueDTO getHouseValue(PropertyRequestDTO data) throws DistrictNotExistsException {
        districtService.districtExists(data.getDistrict().getDistrict_name());
        double price = getHouseTotalSquareMeters(data).getSquareMeters() * data.getDistrict().getDistrict_price();
        return PropertyValueDTO.builder().value(price).build();
    }

    @Override
    public EnvironmentResponseDTO getBiggerEnvironment(PropertyRequestDTO propertyRequestDTO) {
        List<EnvironmentModel> environements = propertyRequestDTO.getEnvironments().stream().map(dto -> mapper.map(dto, EnvironmentModel.class)).collect(Collectors.toList());
        EnvironmentModel bigger = null;
        for (EnvironmentModel room : environements) {
            if (bigger == null) bigger = room;
            if (room.getEnvironment_length() * room.getEnvironment_width() > bigger.getEnvironment_width() * bigger.getEnvironment_length())
                bigger = room;
        }
        return mapper.map(bigger, EnvironmentResponseDTO.class);
    }


    public List<EnvironmentMetersResponseDTO> getMetersPerEnvironment(PropertyRequestDTO propertyRequestDTO) {
        PropertyModel propertyModel = mapper.map(propertyRequestDTO, PropertyModel.class);
        List<EnvironmentMetersResponseDTO> out = new ArrayList<>();
        for (EnvironmentModel room : propertyModel.getEnvironments()) {
            EnvironmentMetersResponseDTO environmentMetersResponseDTO = EnvironmentMetersResponseDTO.builder()
                    .environment_name(room.getEnvironment_name())
                    .square_meters(String.valueOf(getSquareMetersEnvironement(room)))
                    .build();
            out.add(environmentMetersResponseDTO);
        }
        return out;
    }



    private Double getSquareMetersEnvironement(EnvironmentModel environmentModel) {
        return environmentModel.getEnvironment_length() * environmentModel.getEnvironment_width();
    }
}
