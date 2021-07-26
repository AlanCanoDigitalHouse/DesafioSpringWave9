package com.meli.tucasita;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.tucasita.dto.request.CasaRequestDTO;
import com.meli.tucasita.dto.request.Habitacion;
import com.meli.tucasita.dto.response.CasaResponseTO;

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
            habitaciones.put("Kitchen",60.0);
            habitaciones.put("Bathroom",15.0);
            habitaciones.put("Dinning room",40.0);

            return habitaciones;
        }

        public static CasaResponseTO generateCorrectResponse(){
            return CasaResponseTO.builder().metrosCuadradosCasa(115.0).valorCasa(287500.0).
                    habitaciones(generatesCorrectTestArea()).habitacionMasGrande("Kitchen").build();
        }
}
