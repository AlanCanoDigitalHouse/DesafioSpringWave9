package com.mercadolibre.desafio_testing.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class Property implements Habitable {
    private String name;
    private List<Room> rooms;

    public Property(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    @Override
    public double getSquareMeters() {
        return this.rooms.stream().mapToDouble
                (Room::getSquareMeters).sum();
    }

    /* If two rooms have the same size, it will return
    *  the first according to the order in which they
    *  were listed (in a json) for creating the property. */
    public String getBiggerRoom() {
        double maxSqrMts = 0.0;
        String biggerRoom = "";

        for (Room room: this.rooms) {
            if (room.getSquareMeters() > maxSqrMts) {
                maxSqrMts = room.getSquareMeters();
                biggerRoom = room.getName();
            }
        }

        return biggerRoom;
    }
}
