package com.metroscuadrados.metroscuadrados.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private double metrosCuadrados;

    @NotEmpty
    @Max(4000)
    private double valor;

    private HabitacionDTO habitacionMasGrande;
    private List<Double> MetrosCuadradosPorHabitacion;

}
