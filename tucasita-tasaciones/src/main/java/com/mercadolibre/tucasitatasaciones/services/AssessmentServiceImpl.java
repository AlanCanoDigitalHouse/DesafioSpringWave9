package com.mercadolibre.tucasitatasaciones.services;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dtos.req.EnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.dtos.res.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.interfaces.DistrictService;
import com.mercadolibre.tucasitatasaciones.services.interfaces.PropertyAssessmentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements PropertyAssessmentService {

    private final DistrictService districtService;

    public AssessmentServiceImpl(DistrictService districtService) {
        this.districtService = districtService;
    }

    @Override
    public AssessmentDTO calcDimensionM2(PropertyDTO prop) throws DistrictNotFoundException {
        validateIfExists(prop.getDistrict());

        return AssessmentDTO.builder()
                .propName(prop.getPropName())
                .districtName(prop.getDistrict().getDistrictName())
                .dimension(getDimensionOf(prop.getEnvironments()))
                .build();
    }

    @Override
    public AssessmentDTO calcPrice(PropertyDTO prop) throws DistrictNotFoundException {
        validateIfExists(prop.getDistrict());

        Double dimension = getDimensionOf(prop.getEnvironments());
        Double price = dimension * prop.getDistrict().getDistrictPrice();

        return AssessmentDTO.builder()
                .propName(prop.getPropName())
                .districtName(prop.getDistrict().getDistrictName())
                .price(price)
                .build();
    }

    @Override
    public AssessmentDTO getBiggestRoom(PropertyDTO prop) throws DistrictNotFoundException {
        validateIfExists(prop.getDistrict());

        EnvironmentDTO biggest = prop.getEnvironments().stream()
                .peek(r -> r.setDimension(calcSquareMeters(r))) // set area of each room
                .max(Comparator.comparing(EnvironmentDTO::getDimension)) // compare an get the room with biggest area
                .orElse(null);

        return AssessmentDTO.builder()
                .propName(prop.getPropName())
                .districtName(prop.getDistrict().getDistrictName())
                .biggestRoom(biggest)
                .build();
    }

    @Override
    public AssessmentDTO getRoomsDimensions(PropertyDTO prop) throws DistrictNotFoundException {
        validateIfExists(prop.getDistrict());

        List<EnvironmentDTO> rooms = prop.getEnvironments().stream()
                .peek(r -> r.setDimension(calcSquareMeters(r))) // set area of each room
                .collect(Collectors.toList());

        return AssessmentDTO.builder()
                .propName(prop.getPropName())
                .districtName(prop.getDistrict().getDistrictName())
                .roomsDimensions(rooms)
                .build();
    }

    /**
     * Given a List of <b>Environment - Room</b> calculates and return the sum of it's areas in square meters.
     *
     * @param rooms The List of <b>Environment - Room</b> used to calculate the total area.
     * @return The sum of all areas of the <b>Environments - Rooms</b> (square meters).
     */
    private Double getDimensionOf(List<EnvironmentDTO> rooms) {
        return rooms.stream()
                .peek(e -> e.setDimension(calcSquareMeters(e)))
                .reduce(0D, (accu, r) -> accu + r.getDimension(), Double::sum);
    }

    /**
     * Given a <b>Environment - Room</b> with attributes <b>environmentWidth</b> and <b>environmentLength</b>
     * calculate and return it's area in square meters.
     *
     * @param room The <b>Environment - Room</b> to calculate its area.
     * @return The area of the <b>Environment - Room</b> (square meters).
     */
    private Double calcSquareMeters(EnvironmentDTO room) {
        return room.getEnvironmentWidth() * room.getEnvironmentLength();
    }

    /**
     * Call repo to validate if <b>District</b> exists in it.
     *
     * @param district The District to be validated if exists.
     * @throws DistrictNotFoundException If the district does not exists.
     */
    private void validateIfExists(DistrictDTO district) throws DistrictNotFoundException {
        districtService.findDistrictBy(district.getDistrictName());
    }

}
