package com.mercadolibre.desafio_testing.unit.service;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.model.*;
import com.mercadolibre.desafio_testing.repository.*;
import com.mercadolibre.desafio_testing.service.*;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void aPropertyCanBeCreated() throws PropertyNotValidException,
            DuplicatedPropertyException, DistrictDoesNotExistException,
            DuplicateRoomException {
        PropertyDTO property = PropertyDTO.getPropertyDTO(
                ConstantsUtils.PROPERTY_JSONS_ROUTE +
                        "valid_property_creation.json");

        when(this.cityRepository.createProperty(property)).thenReturn(
                new Property("A", null));

        assertEquals(this.cityService.createProperty(property).getResponse(),
                ConstantsUtils.PROPERTY_CREATED);
    }

    @Test
    public void aPropertyShouldHaveNonNullName() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "null_property_name.json"));
        });
    }

    @Test
    public void aPropertyShouldHaveNonVoidName() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "void_property_name.json"));
        });
    }

    @Test
    public void aPropertyNameShouldBeCapitalized() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "uncap_property_name.json"));
        });
    }

    @Test
    public void propertyNameShouldBeBelow31Chars() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "long_property_name.json"));
        });
    }

    @Test
    public void propertyDistrictNameShouldNotBeVoid() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "void_district_name.json"));
        });
    }

    @Test
    public void propertyDistrictNameShouldNotBeTooLong() {
        assertThrows(DistrictNotValidException.class, () -> {
            this.cityService.createDistrict(DistrictDTO.getDistrictDTO(
                    ConstantsUtils.DISTRICT_JSONS_ROUTE +
                            "district_name_too_long.json"));
        });
    }

    @Test
    public void propertyPriceShouldNotBeVoid() {
        assertThrows(DistrictNotValidException.class, () -> {
            this.cityService.createDistrict(DistrictDTO.getDistrictDTO(
                    ConstantsUtils.DISTRICT_JSONS_ROUTE +
                            "void_price.json"));
        });
    }

    @Test
    public void propertyPriceShouldNotBeBiggerThan4000() {
        assertThrows(DistrictNotValidException.class, () -> {
            this.cityService.createDistrict(DistrictDTO.getDistrictDTO(
                    ConstantsUtils.DISTRICT_JSONS_ROUTE +
                            "price_too_big.json"));
        });
    }

    @Test
    public void propertyEnvNameShouldNotBeVoid() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "void_env_name.json"));
        });
    }

    @Test
    public void propertyNameShouldNotHaveSpaces() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "property_name_with_space.json"));
        });
    }

    @Test
    public void propertyEnvNameShouldBeCapitalized() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "non_cap_env_name.json"));
        });
    }

    @Test
    public void propertyEnvNameShouldNotBeMoreThan30CharsLong() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "env_name_too_long.json"));
        });
    }

    @Test
    public void propertyEnvWidthShoudlNotBeVoid() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "env_width_void.json"));
        });
    }

    @Test
    public void propertyEnvWidthShoudlNotBeGreaterThan25() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "env_width_too_big.json"));
        });
    }

    @Test
    public void propertyEnvLengthShoudlNotBeVoid() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "env_length_void.json"));
        });
    }

    @Test
    public void propertyEnvLengthShoudlNotBeGreaterThan33() {
        assertThrows(PropertyNotValidException.class, () -> {
            this.cityService.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "env_length_too_big.json"));
        });
    }

    @Test
    public void squareMetersCanBeCalculatedCorrectly()
            throws PropertyDoesNotExistsException, DistrictDoesNotExistException {
        when(this.cityRepository.getSquareMeters("Casa1")).thenReturn(333.0);
        assertEquals(this.cityService.getSquareMeters("Casa1").getTotal_square_mts(),
                333.0);
    }

    @Test
    public void propertyPriceCanBeCalculatedCorrectly()
            throws DistrictDoesNotExistException, PropertyDoesNotExistsException {
        when(this.cityRepository.getPrice("Casa1")).thenReturn(193600.0);
        assertEquals(this.cityService.getPrice("Casa1").getPrice(), 193600);
    }

    @Test
    public void propertyBiggestRoomCanBeCalculatedOk()
            throws PropertyDoesNotExistsException {
        when(this.cityRepository.getBiggestRoom("Casa1"))
                .thenReturn("Ambiente1");
        assertEquals(this.cityService.getBiggestRoom("Casa1")
                .getBig_room(), "Ambiente1");
    }

    @Test
    public void squareMetersOfRoomsCanBeCalculatedCorrectly()
            throws PropertyDoesNotExistsException {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Amb1", 11, 11));
        when(this.cityRepository.getRoomsOf("Casa1")).thenReturn(rooms);
        assertEquals(this.cityService.getSquareMetersOfRooms("Casa1")
                .getRooms().get(0).getEnvironment_square_meters(), 121);
    }
}
