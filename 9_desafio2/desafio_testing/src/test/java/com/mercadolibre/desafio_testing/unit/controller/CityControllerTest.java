package com.mercadolibre.desafio_testing.unit.controller;

import com.mercadolibre.desafio_testing.controller.CityController;
import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.dto.response.*;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.service.*;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CityControllerTest {
    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    private final PropertyDTO property = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property_creation.json");

    private final DistrictDTO district = DistrictDTO.getDistrictDTO(
            ConstantsUtils.DISTRICT_JSONS_ROUTE +
                    "valid_district_creation.json");

    @Test
    public void propertyCreationEndPointRespondsCorrectly()
            throws DistrictDoesNotExistException,
            PropertyNotValidException, DuplicatedPropertyException,
            DuplicateRoomException {
        when(this.cityService.createProperty(this.property)).thenReturn(
                (new PropertyCreationResponseDTO()));

        assertEquals(this.cityController.createProperty(this.property)
                .getBody().getResponse(), ConstantsUtils.PROPERTY_CREATED);
    }

    @Test
    public void districtCreationEndPointRespondsCorrectly()
            throws DistrictNotValidException,
            DistrictAlreadyExistsException {
        when(this.cityService.createDistrict(this.district)).thenReturn(
                (new DistrictCreationReponseDTO()));

        assertEquals(this.cityController.createDistrict(this.district)
                .getBody().getResponse(), ConstantsUtils.DISTRICT_CREATED);
    }

    @Test
    public void squareMetersEndPointRespondsCorrectly()
            throws DistrictDoesNotExistException,
            PropertyDoesNotExistsException {
        when(this.cityService.getSquareMeters("Casa1")).thenReturn(
                new PropertySquareMetersResponseDTO("Casa1", 111));

        assertEquals(this.cityController.getSquareMeters("Casa1")
                .getBody().getTotal_square_mts(), 111);
    }

    @Test
    public void priceEndPointRespondsCorrectly()
            throws DistrictDoesNotExistException,
            PropertyDoesNotExistsException {
        when(this.cityService.getPrice("Casa1")).thenReturn(
                new PropertyPriceResponseDTO(100));

        assertEquals(this.cityController.getPrice("Casa1")
                .getBody().getPrice(), 100);
    }

    @Test
    public void biggerRoomEndpointRespondsCorrectly()
            throws PropertyDoesNotExistsException {
        when(this.cityService.getBiggestRoom("Casa1")).thenReturn(
                new BiggestRoomResponseDTO("Amb1"));

        assertEquals(this.cityController.getBiggestRoom("Casa1").getBody()
                .getBig_room(), "Amb1");
    }

    @Test
    public void squareMetersOfRoomsEndpointRespondsCorrectly()
            throws PropertyDoesNotExistsException {
        List<RoomSquareMetersResponseDTO> rooms =  new ArrayList<>();
        rooms.add(new RoomSquareMetersResponseDTO("Amb1", 20));
        when(this.cityService.getSquareMetersOfRooms("Casa1")).thenReturn(
                new RoomsSquareMetersResponseDTO(rooms));
        assertEquals(this.cityController.getSquareMetersOfRooms("Casa1").getBody()
        .getRooms().get(0).getEnvironment_square_meters(), 20);
    }
}
