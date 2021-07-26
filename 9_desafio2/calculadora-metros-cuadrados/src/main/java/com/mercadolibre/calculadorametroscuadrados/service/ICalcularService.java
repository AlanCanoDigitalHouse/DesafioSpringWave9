package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.AmbienteDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.AreaAmbienteResponseDTO;

import java.util.ArrayList;

public interface ICalcularService {
    Double calcularMetrosCuadrados();
    Double calcuarValorTotal();
    AmbienteDTO calcularAmbienteMasGrande();
    ArrayList<AreaAmbienteResponseDTO> listarAreas();
}
