package com.squareMeter.service;

import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.EnvironmentResponseDTO;
import com.squareMeter.dto.response.PropertyValueDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.EnvironmentModel;
import com.squareMeter.model.PropertyModel;
import com.squareMeter.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/* Note about dozer mapper
This library was selected becouse de advantages to make more readable the code
Created in 2017 with 1900 stars, it can be considered a stable library and not out of anywhere
 */
@Service
@RequiredArgsConstructor
public class PropertyService implements IPropertyService {
    //Wrapper of DozerBeanMapper to map a DTO to a model using only a one line
    final private Mapper mapper;
    final private DistrictService districtService;

    /**
     * Calculate the total square meters of a house:
     * Get the square meters of a environment = width * length
     * Then sum all this numbers and get the total square meters
     * @param propertyRequestDTO info of the propery
     * @return the quantity of meters
     */
    @Override
    public PropertySquareMetersResponseDTO getHouseTotalSquareMeters(PropertyRequestDTO propertyRequestDTO){
        PropertyModel propertyModel = mapper.map(propertyRequestDTO, PropertyModel.class);
        double total = propertyModel.getEnvironments().stream().mapToDouble(room -> room.getEnvironment_width() * room.getEnvironment_length()).sum();
        return new PropertySquareMetersResponseDTO(total);
    }

    /**
     * Obtain the value of a property
     * @param data property data
     * @return Value of the property
     * @throws DistrictNotExistsException in case of the district don't exists
     */
    @Override
    public PropertyValueDTO getHouseValue(PropertyRequestDTO data) throws DistrictNotExistsException {
        districtService.districtExists(data.getDistrict().getDistrict_name());
        double price = getHouseTotalSquareMeters(data).getSquareMeters()*data.getDistrict().getDistrict_price();
        return PropertyValueDTO.builder().value(price).build();
    }

    /**
     * Obtain the biggest environment of a property
     * @param propertyRequestDTO data property
     * @return the biggest environment
     */
    @Override
    public EnvironmentResponseDTO getBiggerEnvironment(PropertyRequestDTO propertyRequestDTO) {
        List<EnvironmentModel> environements = propertyRequestDTO.getEnvironments().stream().map(dto -> mapper.map(dto, EnvironmentModel.class)).collect(Collectors.toList());
        EnvironmentModel bigger = null;
        for (EnvironmentModel room : environements) {
            if(bigger == null) bigger = room;
            if(room.getEnvironment_length()* room.getEnvironment_width() > bigger.getEnvironment_width()* bigger.getEnvironment_length())
                bigger = room;
        }
        return mapper.map(bigger, EnvironmentResponseDTO.class);
    }

    /*public List<AmbientMetersDTO> getMetersPerRoom(PropertyModel propertyModel) {
        List<AmbientMetersDTO> out = new ArrayList<>();
        for (EnvironmentModel room : propertyModel.getEnvironmentModelList()) {
            AmbientMetersDTO ambientMetersDTO = AmbientMetersDTO.builder()
                    .name(room.getEnvironment_name())
                    .meters(room.getEnvironment_width() * room.getEnvironment_length())
                    .build();
            out.add(ambientMetersDTO);
        }
        return out;
    }*/
}
