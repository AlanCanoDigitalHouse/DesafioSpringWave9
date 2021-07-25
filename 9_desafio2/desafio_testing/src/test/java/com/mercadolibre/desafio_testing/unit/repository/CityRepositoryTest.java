package com.mercadolibre.desafio_testing.unit.repository;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.repository.*;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityRepositoryTest {
    private CityRepository cityRepository = new CityRepository();

    private final DistrictDTO district = DistrictDTO.getDistrictDTO(
            ConstantsUtils.DISTRICT_JSONS_ROUTE +
                    "valid_district_creation.json");

    private final PropertyDTO property = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property_creation.json");

    private final PropertyDTO noRoomsProperty = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property_no_rooms.json");

    private final DistrictDTO district2 = DistrictDTO.getDistrictDTO(
            ConstantsUtils.DISTRICT_JSONS_ROUTE +
                    "valid_district2.json");

    private final PropertyDTO property2 = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property2.json");

    @Test
    public void aPropertyCanBeCreated() throws DuplicatedPropertyException,
            DistrictDoesNotExistException, DistrictAlreadyExistsException,
            DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.property);
        assertNotNull(this.cityRepository.getDistrict("barrio1"));
        assertEquals(this.cityRepository.getDistrict("barrio1").getName(),
                "barrio1");
    }

    @Test
    public void aPropertyWithTheSameNameInTheSameDistrictCannotBeCreated()
            throws DuplicatedPropertyException, DistrictDoesNotExistException,
            DistrictAlreadyExistsException, DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.property);
        assertThrows(DuplicatedPropertyException.class, () -> {
            this.cityRepository.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "valid_property_creation.json"));
        });
    }

    @Test
    public void squareMetersCalculationIsOk()
            throws PropertyDoesNotExistsException,
            DuplicatedPropertyException, DistrictDoesNotExistException,
            DistrictAlreadyExistsException, DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.property);
        assertEquals(this.cityRepository.getSquareMeters("Casa1"), 242);
    }

    @Test
    public void squareMetersCalculationIsOk2()
            throws PropertyDoesNotExistsException,
            DuplicatedPropertyException, DistrictDoesNotExistException,
            DistrictAlreadyExistsException, DuplicateRoomException {
        this.cityRepository.createDistrict(this.district2);
        this.cityRepository.createProperty(this.property2);
        assertEquals(this.cityRepository.getSquareMeters("Casa2"), 570);
    }

    @Test
    public void aPropertyCannotBeCreatedIfItsDistrictDoesNotExists() {
        assertThrows(DistrictDoesNotExistException.class, () -> {
            this.cityRepository.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.PROPERTY_JSONS_ROUTE +
                            "valid_property_creation.json"));
        });
    }

    @Test
    public void aPropertyCannotBeCreatedIfItHasDuplicatedRooms()
            throws DistrictAlreadyExistsException {
        this.cityRepository.createDistrict(this.district2);
        assertThrows(DuplicateRoomException.class, () -> {
            this.cityRepository.createProperty(PropertyDTO.getPropertyDTO(
                    ConstantsUtils.ROOMS_JSONS_ROUTE +
                            "duplicated_room_name.json"));
        });
    }

    @Test
    public void propertyPriceIsCalculatedOk()
            throws DistrictAlreadyExistsException,
            DistrictDoesNotExistException, DuplicatedPropertyException,
            PropertyDoesNotExistsException, DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.property);
        assertEquals(this.cityRepository.getPrice("Casa1"), 193600);
    }

    @Test
    public void propertyPriceIsCalculatedOk2()
            throws DistrictAlreadyExistsException,
            DistrictDoesNotExistException, DuplicatedPropertyException,
            PropertyDoesNotExistsException, DuplicateRoomException {
        this.cityRepository.createDistrict(this.district2);
        this.cityRepository.createProperty(this.property2);
        assertEquals(this.cityRepository.getPrice("Casa2"), 633332.7);
    }

    @Test
    public void propertyPriceCannotBeCalculatedIfDoesNotExists()
            throws DistrictAlreadyExistsException {
        this.cityRepository.createDistrict(this.district);

        assertThrows(PropertyDoesNotExistsException.class, () -> {
            this.cityRepository.getPrice("Casa1");
        });
    }

    @Test
    public void propertyPriceCannotBeCalculatedIfDistrictDoesNotExists() {
        assertThrows(PropertyDoesNotExistsException.class, () -> {
            this.cityRepository.getPrice("Casa1");
        });
    }

    @Test
    public void propertyHasTheCorrectBiggestRoom()
            throws DistrictDoesNotExistException, DuplicatedPropertyException,
            DistrictAlreadyExistsException, PropertyDoesNotExistsException,
            DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.property);
        assertEquals(this.cityRepository.getBiggestRoom("Casa1"), "Ambiente1");
    }

    @Test
    public void propertyHasTheCorrectBiggestRoom2()
            throws DistrictDoesNotExistException, DuplicatedPropertyException,
            DistrictAlreadyExistsException, PropertyDoesNotExistsException,
            DuplicateRoomException {
        this.cityRepository.createDistrict(this.district2);
        this.cityRepository.createProperty(this.property2);
        assertEquals(this.cityRepository.getBiggestRoom("Casa2"), "Ambiente2");
    }

    @Test
    public void propertyWithNoRoomsHas0SquareMeters()
            throws DistrictDoesNotExistException, DuplicatedPropertyException,
            DistrictAlreadyExistsException, PropertyDoesNotExistsException,
            DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.noRoomsProperty);
        assertEquals(this.cityRepository.getSquareMeters("Casa0"), 0);
    }

    @Test
    public void propertyWithNoRoomsHas0Price()
            throws DistrictDoesNotExistException, DuplicatedPropertyException,
            DistrictAlreadyExistsException, PropertyDoesNotExistsException,
            DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.noRoomsProperty);
        assertEquals(this.cityRepository.getSquareMeters("Casa0"), 0);
    }

    @Test
    public void propertyWithNoRoomsHasNoBiggestRoom()
            throws DistrictDoesNotExistException, DuplicatedPropertyException,
            DistrictAlreadyExistsException, PropertyDoesNotExistsException,
            DuplicateRoomException {
        this.cityRepository.createDistrict(this.district);
        this.cityRepository.createProperty(this.noRoomsProperty);
        assertEquals(this.cityRepository.getBiggestRoom("Casa0"), "");
    }
}
