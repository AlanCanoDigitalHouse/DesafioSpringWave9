package com.mercadolibre.desafio_testing.repository;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.model.*;

import java.util.ArrayList;
import java.util.List;

public interface ICityRepository {
    public abstract Property createProperty(PropertyDTO property)
            throws DuplicatedPropertyException, DistrictDoesNotExistException,
            DuplicateRoomException;

    public abstract District getDistrict(String districtName)
            throws DistrictDoesNotExistException;

    public abstract void createDistrict(DistrictDTO district)
            throws DistrictAlreadyExistsException;

    public abstract double getSquareMeters(String name)
            throws PropertyDoesNotExistsException,
            DistrictDoesNotExistException;

    public abstract double getPrice(String name)
            throws PropertyDoesNotExistsException;

    public abstract String getBiggestRoom(String name)
            throws PropertyDoesNotExistsException;

    public abstract List<Room> getRoomsOf(String name) throws PropertyDoesNotExistsException;
}
