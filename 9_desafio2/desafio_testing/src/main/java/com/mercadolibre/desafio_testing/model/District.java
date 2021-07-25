package com.mercadolibre.desafio_testing.model;

import com.mercadolibre.desafio_testing.exception.DuplicatedPropertyException;
import com.mercadolibre.desafio_testing.exception.PropertyDoesNotExistsException;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class District {
    private String name;
    private double priceInDollarsPerSquareMeter;
    private Map<String, Property> properties;

    public District(String name, double priceInDollarsPerSquareMeter) {
        this.name = name;
        this.priceInDollarsPerSquareMeter = priceInDollarsPerSquareMeter;
        this.properties = new HashMap<>();
    }

    public Property addProperty(String propertyName,
                                List<Room> rooms)
            throws DuplicatedPropertyException {
        Property property = new Property(propertyName, rooms);

        if (this.properties.getOrDefault(propertyName, null) != null) {
            throw new DuplicatedPropertyException();
        }

        this.properties.put(property.getName(), property);
        return property;
    }

    public boolean isPropertyInHere(String name) {
        return this.properties.getOrDefault(name, null) != null;
    }

    public Property getProperty(String name)
            throws PropertyDoesNotExistsException {
        if (this.properties.getOrDefault(name, null) == null) {
            throw new PropertyDoesNotExistsException();
        }

        return this.properties.get(name);
    }

    public double getSquareMetersOf(String propertyName)
            throws PropertyDoesNotExistsException {
        return this.getProperty(propertyName).getSquareMeters();
    }

    public double getPriceOf(String name)
            throws PropertyDoesNotExistsException {
        return this.getPriceInDollarsPerSquareMeter() *
                this.getSquareMetersOf(name);
    }

    public String getBiggerRoomOf(String name)
            throws PropertyDoesNotExistsException {
        return this.getProperty(name).getBiggerRoom();
    }

    public List<Room> getRoomsOf(String name)
            throws PropertyDoesNotExistsException {
        return this.getProperty(name).getRooms();
    }
}
