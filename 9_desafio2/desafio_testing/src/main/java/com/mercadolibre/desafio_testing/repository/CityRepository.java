package com.mercadolibre.desafio_testing.repository;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.exception.*;
import org.springframework.stereotype.Repository;
import com.mercadolibre.desafio_testing.model.*;

import java.util.*;

@Repository
public class CityRepository implements ICityRepository {
    private Map<String, District> districts;

    public CityRepository() {
        this.districts = new HashMap<>();
    }

    public void createDistrict(DistrictDTO district)
            throws DistrictAlreadyExistsException {
        if (this.districts.getOrDefault(district.getDistrict_name(), null)
                != null) {
            throw new DistrictAlreadyExistsException();
        }

        this.districts.put(district.getDistrict_name(),
                new District(district.getDistrict_name(),
                        district.getDistrict_price()));
    }

    private void checkDuplicateProperty(String propertyName)
            throws DuplicatedPropertyException {
        for (String dName : districts.keySet()) {
            if (districts.get(dName).isPropertyInHere(propertyName)) {
                throw new DuplicatedPropertyException();
            }
        }
    }

    private List<Room> getRoomsOfDto(PropertyDTO property)
            throws DuplicateRoomException {
        List<Room> rooms = new ArrayList<>();
        Map<String, Boolean> roomNames = new HashMap<>();

        for (RoomDTO room : property.getEnvironments()) {
            if (roomNames.getOrDefault(room.getEnvironment_name(), false)) {
                throw new DuplicateRoomException();
            } else {
                roomNames.put(room.getEnvironment_name(), true);
            }

            rooms.add(new Room(room.getEnvironment_name(),
                    room.getEnvironment_length(),
                    room.getEnvironment_width()));
        }

        return rooms;
    }

    public Property createProperty(PropertyDTO property)
            throws DuplicatedPropertyException, DistrictDoesNotExistException,
            DuplicateRoomException {
        this.checkDuplicateProperty(property.getProp_name());
        return this.getDistrict(property.getDistrict_name())
                .addProperty(property.getProp_name(), getRoomsOfDto(property));
    }

    private District getDistrictOfProperty(String name)
            throws PropertyDoesNotExistsException {
        for (String districtName : districts.keySet()) {
            if (districts.get(districtName).isPropertyInHere(name)) {
                return districts.get(districtName);
            }
        }

        throw new PropertyDoesNotExistsException();
    }

    public District getDistrict(String districtName)
            throws DistrictDoesNotExistException {
        if (this.districts.getOrDefault(districtName, null) == null) {
            throw new DistrictDoesNotExistException();
        }

        return this.districts.get(districtName);
    }

    public double getSquareMeters(String name)
            throws PropertyDoesNotExistsException,
            DistrictDoesNotExistException {
        return this.getDistrictOfProperty(name).getSquareMetersOf(name);
    }

    public double getPrice(String name)
            throws PropertyDoesNotExistsException {
        return this.getDistrictOfProperty(name).getPriceOf(name);
    }

    public String getBiggestRoom(String name)
            throws PropertyDoesNotExistsException {
        return this.getDistrictOfProperty(name).getBiggerRoomOf(name);
    }

    public List<Room> getRoomsOf(String name)
            throws PropertyDoesNotExistsException {
        return this.getDistrictOfProperty(name).getRoomsOf(name);
    }
}
