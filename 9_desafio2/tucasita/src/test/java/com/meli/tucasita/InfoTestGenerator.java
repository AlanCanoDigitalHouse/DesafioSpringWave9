package com.meli.tucasita;

import com.meli.tucasita.dto.request.CasaRequestDTO;
import com.meli.tucasita.dto.request.Habitacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoTestGenerator{

        public static CasaRequestDTO generatesCorrectTestInfo(){
            Habitacion habitacion1 = Habitacion.builder().nombre("Dinning room").ancho(5).largo(8).build();
            Habitacion habitacion2 = Habitacion.builder().nombre("Kitchen").ancho(5).largo(12).build();
            Habitacion habitacion3 = Habitacion.builder().nombre("Bathroom").ancho(3).largo(5).build();

            List<Habitacion> habitaciones = new ArrayList<>();

            habitaciones.add(habitacion1);
            habitaciones.add(habitacion2);
            habitaciones.add(habitacion3);

            return CasaRequestDTO.builder().nombre("Casa roja").distrito("Trece").
                    district_price(2500.0).habitacion(habitaciones).build();
        }

        public static Map<String, Double> generatesCorrectTestArea(){
            Map<String, Double> habitaciones = new HashMap<>();
            habitaciones.put("Dinning room",40.0);
            habitaciones.put("Kitchen",60.0);
            habitaciones.put("Bathroom",15.0);

            return habitaciones;
        }
}
