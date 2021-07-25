package com.mercadolibre.desafio_testing.service;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.dto.response.*;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityService implements ICityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public PropertyCreationResponseDTO createProperty(PropertyDTO property)
            throws PropertyNotValidException,
            DuplicatedPropertyException, DistrictDoesNotExistException,
            DuplicateRoomException {
        property.validate();
        this.cityRepository.createProperty(property);
        return new PropertyCreationResponseDTO();
    }

    public PropertySquareMetersResponseDTO getSquareMeters(String name)
            throws PropertyDoesNotExistsException,
            DistrictDoesNotExistException {
        return new PropertySquareMetersResponseDTO(name,
                this.cityRepository.getSquareMeters(name));
    }

    public DistrictCreationReponseDTO createDistrict(DistrictDTO district)
            throws DistrictAlreadyExistsException,
            DistrictNotValidException {
        district.validate();
        this.cityRepository.createDistrict(district);
        return new DistrictCreationReponseDTO();
    }

    public PropertyPriceResponseDTO getPrice(String name)
            throws PropertyDoesNotExistsException {
        return new PropertyPriceResponseDTO(
                this.cityRepository.getPrice(name));
    }

    public BiggestRoomResponseDTO getBiggestRoom(String name)
            throws PropertyDoesNotExistsException {
        return new BiggestRoomResponseDTO(this.cityRepository.
                getBiggestRoom(name));
    }

    public RoomsSquareMetersResponseDTO getSquareMetersOfRooms(String name)
            throws PropertyDoesNotExistsException {
        List<RoomSquareMetersResponseDTO> rooms = new ArrayList<>();

        this.cityRepository.getRoomsOf(name).forEach((room) -> {
            rooms.add(new RoomSquareMetersResponseDTO(
                    room.getName(), room.getSquareMeters()));
        });

        return new RoomsSquareMetersResponseDTO(rooms);
    }
}
