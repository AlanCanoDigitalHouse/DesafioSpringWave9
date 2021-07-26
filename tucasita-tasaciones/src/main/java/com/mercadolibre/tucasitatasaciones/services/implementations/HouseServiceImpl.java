package com.mercadolibre.tucasitatasaciones.services.implementations;

import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dtos.request.EnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.IDistrictService;
import com.mercadolibre.tucasitatasaciones.services.IHouseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("houseService")
public class HouseServiceImpl implements IHouseService {

    private final IDistrictService iDistrictService;

    public HouseServiceImpl(@Qualifier("districtService") IDistrictService iDistrictService) {
        this.iDistrictService = iDistrictService;
    }

    @Override
    public AssessmentDTO assessmentSquareMeters(HouseDTO house) throws DistrictNotFoundException {
        checkIfExists(house.getDistrict());
        return AssessmentDTO.builder()
                .prop_name(house.getProp_name())
                .squareMeters(this.calculateAllRoomsSquareMeters(house.getEnvironments()))
                .build();
    }

    @Override
    public AssessmentDTO assessmentSquareMetersEachRoom(HouseDTO house) throws DistrictNotFoundException {
        checkIfExists(house.getDistrict());

        List<EnvironmentDTO> roomsWithSquare = house.getEnvironments().stream()
                .peek(room -> room.setSquareMeters(calculateRoom(room)))
                .collect(Collectors.toList());

        return AssessmentDTO.builder()
                .prop_name(house.getProp_name())
                .environments(roomsWithSquare)
                .build();
    }

    @Override
    public AssessmentDTO assessmentBiggestRoom(HouseDTO house) throws DistrictNotFoundException {
        checkIfExists(house.getDistrict());

        EnvironmentDTO biggestRoom = house.getEnvironments().stream()
                .peek(room -> room.setSquareMeters(calculateRoom(room)))
                .max(Comparator.comparing(EnvironmentDTO::getSquareMeters))
                .orElse(null);

        return AssessmentDTO.builder()
                .prop_name(house.getProp_name())
                .biggestRoom(biggestRoom)
                .build();
    }

    @Override
    public AssessmentDTO assessmentPrice(HouseDTO house) throws DistrictNotFoundException {
        checkIfExists(house.getDistrict());

        Double assessmentSquareMeters = calculateAllRoomsSquareMeters(house.getEnvironments());
        Double totalPrice = assessmentSquareMeters * house.getDistrict().getDistrict_price();

        return AssessmentDTO.builder()
                .prop_name(house.getProp_name())
                .propertyPrice(totalPrice)
                .build();
    }

    private void checkIfExists(DistrictDTO district) throws DistrictNotFoundException {
        this.iDistrictService.findByName(district.getDistrict_name());
    }

    private Double calculateAllRoomsSquareMeters(List<EnvironmentDTO> listRooms){
        return listRooms.stream()
                .peek(e -> e.setSquareMeters(calculateRoom(e)))
                .reduce(0D, (accu,r) -> accu + r.getSquareMeters(), Double::sum);
    }

    private Double calculateRoom(EnvironmentDTO room){
        return room.getEnvironment_length() * room.getEnvironment_width();
    }
}
