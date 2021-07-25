package com.mercadolibre.desafio_testing.service;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.dto.response.*;
import com.mercadolibre.desafio_testing.exception.*;

public interface ICityService {
    public abstract PropertyCreationResponseDTO
    createProperty(PropertyDTO property)
            throws PropertyNotValidException, DuplicatedPropertyException,
            DistrictDoesNotExistException, DuplicateRoomException;

    public abstract PropertySquareMetersResponseDTO
    getSquareMeters(String name) throws PropertyDoesNotExistsException,
            DistrictDoesNotExistException;

    public abstract DistrictCreationReponseDTO
    createDistrict(DistrictDTO districtName)
            throws DistrictAlreadyExistsException,
            PropertyNotValidException, DistrictNotValidException;

    public abstract PropertyPriceResponseDTO getPrice(String name)
            throws PropertyDoesNotExistsException;

    public abstract RoomsSquareMetersResponseDTO
    getSquareMetersOfRooms(String name) throws PropertyDoesNotExistsException;
}
