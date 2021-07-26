package com.meli.tucasita.service;

import com.meli.tucasita.dto.Distrito;
import com.meli.tucasita.dto.request.CasaRequestDTO;
import com.meli.tucasita.dto.request.Habitacion;
import com.meli.tucasita.dto.response.CasaResponseDTO;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.DiferentDistrictPriceException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import com.meli.tucasita.repository.CasaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CasaService implements CasaServiceInterface{

    @Autowired
    private CasaRepository casaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CasaRepository.class);

    @Override
    public ResponseEntity<CasaResponseDTO> calcularMetroPropiedad(CasaRequestDTO casaRequestDTO) throws NoDistrictFoundException, DataBaseException, DiferentDistrictPriceException {
        LOGGER.info("Procesando informaciòn obtenida");

        Map<String, Double> habitaciones = calcularAreaHabitaciones(casaRequestDTO);
        Distrito distrito = casaRepository.obtenerDistrito(casaRequestDTO.getDistrito());
        Double tamanoCasa;

        if(casaRequestDTO.getDistrict_price().equals(distrito.getPricePerMeter())){
            tamanoCasa=obtenerTamanoCasa(casaRequestDTO.getHabitacion(),habitaciones);
            return new ResponseEntity<>(new CasaResponseDTO(tamanoCasa,
                    tamanoCasa*(distrito.getPricePerMeter()), habitaciones,
                    obtenerHabitacionMasGrande(habitaciones)), HttpStatus.OK);
        }else
            throw new DiferentDistrictPriceException();
    }

    public Map<String, Double> calcularAreaHabitaciones(CasaRequestDTO casaRequestDTO){
        LOGGER.info("Calculando area de las habitaciones");
        Map<String, Double> habitacion = new HashMap<>();

        casaRequestDTO.getHabitacion().forEach(h -> habitacion.put(h.getNombre(),h.getAncho()*h.getLargo()));

        return habitacion;
    }

    public String obtenerHabitacionMasGrande(Map<String, Double> habitaciones){
        LOGGER.info("Obteniendo la habitacion mas grande");
        Double maxValueInMap=(Collections.max(habitaciones.values()));
        String nombreHabitacion = "";
        for (Map.Entry<String, Double> entry : habitaciones.entrySet()) {
            if (entry.getValue().equals(maxValueInMap)) {
                nombreHabitacion=entry.getKey();
            }
        }
        return nombreHabitacion;
    }

    public Double obtenerTamanoCasa(List<Habitacion> habitacionList, Map<String, Double> habitaciones){
        LOGGER.info("Obteniendo la medida final de la propiedad");
        AtomicReference<Double> tamanoCasa= new AtomicReference<>(0.0);
        habitacionList.forEach(h-> tamanoCasa.updateAndGet(v -> v + habitaciones.get(h.getNombre())));
        return tamanoCasa.get();
    }
}
