package com.mercadolibre.desafio_testing.model;

import lombok.*;

@Getter
@Setter
public class Room implements Habitable {
    private String name;
    private double lengthInMeters;
    private double widthInMeters;

    public Room(String name, double lengthInMeters, double widthInMeters) {
        this.name = name;
        this.lengthInMeters = lengthInMeters;
        this.widthInMeters = widthInMeters;
    }

    @Override
    public double getSquareMeters() {
        return this.lengthInMeters * this.widthInMeters;
    }
}
