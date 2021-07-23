package com.squareMeter.service;

import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.PropertyValueDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.PropertyModel;
import com.squareMeter.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public PropertySquareMetersResponseDTO getHouseTotalSquareMeters(PropertyRequestDTO propertyRequestDTO) throws DistrictNotExistsException {
        PropertyModel propertyModel = mapper.map(propertyRequestDTO, PropertyModel.class);
        double total = propertyModel.getEnvironments().stream().mapToDouble(room -> room.getEnvironment_width() * room.getEnvironment_length()).sum();
        return new PropertySquareMetersResponseDTO(total);
    }

    @Override
    public PropertyValueDTO getHouseValue(PropertyRequestDTO data) throws DistrictNotExistsException {
        districtService.districtExists(data.getDistrict().getDistrict_name());
        double price = getHouseTotalSquareMeters(data).getSquareMeters()*data.getDistrict().getDistrict_price();
        return PropertyValueDTO.builder().value(price).build();
    }

    /*public PropertyValueDTO getHouseValue(PropertyModel propertyModel) {
        double price;
        List<EnvironmentModel> rooms = propertyModel.getEnvironmentModelList();
        price = rooms.stream().mapToDouble(room -> room.getEnvironment_width() * room.getEnvironment_length() * 800).sum();

        PropertyValueDTO houseValueDTO = PropertyValueDTO.builder()
                .value(price)
                .build();

        return houseValueDTO;
    }



    public EnvironmentModel getBiggerRoom(PropertyModel propertyModel) {
        EnvironmentModel actualRoom = propertyModel.getEnvironmentModelList().get(0);
        for (EnvironmentModel room : propertyModel.getEnvironmentModelList()) {
            double metersActual = actualRoom.getEnvironment_length() * actualRoom.getEnvironment_width();
            double metersNew = room.getEnvironment_length() * room.getEnvironment_width();
            if (metersNew > metersActual) {
                actualRoom = room;
            }
        }
        return actualRoom;
    }

    public List<AmbientMetersDTO> getMetersPerRoom(PropertyModel propertyModel) {
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
