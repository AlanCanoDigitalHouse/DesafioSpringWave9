package com.metroscuadrados.metroscuadrados.Services;

import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.HabitacionDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrioImp;

import java.util.ArrayList;
import java.util.List;

public interface ICasaService {
    double metrosCuadradosPorHabitacion(HabitacionDTO habitacion);

    double metrosCuadrados(CasaDTO casa);

    double valorDeLaCasa(CasaDTO casa) throws NotFoundException;

    HabitacionDTO habitacionMasGrande(CasaDTO casa);

    List<Double> listaDeMetrosCuadradosPorHabitacion(CasaDTO casa);

}
