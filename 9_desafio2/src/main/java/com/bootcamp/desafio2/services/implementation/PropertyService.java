package com.bootcamp.desafio2.services.implementation;

import com.bootcamp.desafio2.dtos.request.CasaDto;
import com.bootcamp.desafio2.dtos.request.HabitacionDto;
import com.bootcamp.desafio2.dtos.response.CasaResponseDto;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.stereotype.Service;

@Service
public class PropertyService implements IPropertyService {

    private final Double VALOR_METRO_CUADRADO = 800D;


    @Override
    public CasaResponseDto calcularArea(CasaDto casa) throws ErrorMessage {
        Double areaTotal = 0D;
        Double areaMax = 0D;
        HabitacionDto aux = new HabitacionDto();
        try {
            for (HabitacionDto habitacion: casa.getHabitaciones()) {
                Double area = habitacion.getAncho() * habitacion.getLargo();
                habitacion.setMetrosCuadrados(area);
                areaTotal = areaTotal + area;
                if (area > areaMax) {
                    aux = habitacion;
                    areaMax = area;
                }
            }
            return new CasaResponseDto(areaTotal, areaTotal * VALOR_METRO_CUADRADO, aux, casa.getHabitaciones());
        } catch (Exception e) {
            throw new ErrorMessage("Error en la capa de servicio");
        }
    }

}
