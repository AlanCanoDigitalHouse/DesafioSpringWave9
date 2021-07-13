package com.example.desafiospring.utils;

import com.example.desafiospring.dtos.response.ClientResponseDto;
import com.example.desafiospring.dtos.response.SellerResponseDto;
import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.TypeNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Factory {
    public static UserResponseDto factoryUser(UserResponseDto user, String type) {
        if (type.equals("Client")) {
            return new ClientResponseDto(user.getId(), user.getUserName());
        } else if (type.equals("Seller")) {
            return new SellerResponseDto(user.getId(), user.getUserName());
        } else {
            throw new TypeNotFoundException("The type given in the argument does not exist");
        }
    }

    public static List<UserResponseDto> generateData() {
        List<UserResponseDto> listGenerated = new ArrayList<>();
        String listOfNames[] = {"Suhayb Schroeder",
                "Reagan Mathews",
                "Alia Chambers",
                "Annika Lowry",
                "Hafsa Mathis",
                "Zak Stewart",
                "Umaiza Harmon",
                "Giselle Butt",
                "Layla-Rose Dunkley",
                "Paolo Rocha",
                "Bernice Charlton",
                "Aviana Dunne",
                "Inaayah Wilde",
                "Aya Norman",
                "Arslan Torres",
                "Habib Rogers",
                "Guy Hawes",
                "Sameer Vu",
                "Catrina Lindsay",
                "Leroy Wallace",
                "Eleri Howard",
                "Ammaarah Tyson",
                "Coby Burton",
                "Amman Phelps",
                "Valentina Guy",
                "Steve Buckner",
                "Trevor Mendoza",
                "Olivia-Rose Hendricks",
                "Lucian Hulme",
                "Hayleigh Ratliff"};
        for (int i = 0; i < 30; i++) {
            UserResponseDto user = new UserResponseDto(i, listOfNames[i]);
            if (i < 20) {
                var clients = factoryUser(user, "Client");
                listGenerated.add(clients);
            } else {
                var sellers = factoryUser(user, "Seller");
                listGenerated.add(sellers);
            }
        }
        return listGenerated;
    }

    public static Integer generateId(){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return n;
    }
}

