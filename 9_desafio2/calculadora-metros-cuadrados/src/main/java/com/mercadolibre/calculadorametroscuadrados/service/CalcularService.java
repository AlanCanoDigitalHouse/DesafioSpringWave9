package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.AreaAmbienteResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.BarrioDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropiedadDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.AmbienteDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.BarrioNoEncontradoException;
import com.mercadolibre.calculadorametroscuadrados.repositories.PropiedadRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalcularService implements ICalcularService {

  PropiedadRepository propiedadRepository;

  public CalcularService(PropiedadRepository propiedadRepository){
    this.propiedadRepository = propiedadRepository;
  }

  public BarrioDTO inicializar(PropiedadDTO propiedad) throws BarrioNoEncontradoException {
    BarrioDTO barrio=propiedadRepository.inicializar(propiedad);
    return barrio;
  }


  public Double calcularMetrosCuadrados() {
    Double area=0.0;
    for (AmbienteDTO ambiente:propiedadRepository.getPropiedad().getEnvironments()){
      area+= ambiente.getEnvironment_length()* ambiente.getEnvironment_width();
    }
    return area;

  }

  public Double calcuarValorTotal() {
    return calcularMetrosCuadrados()*propiedadRepository.getPropiedad().getDistrict().getDistrict_price();
  }

  public AmbienteDTO calcularAmbienteMasGrande() {
    AmbienteDTO ambienteMasGrande = null;
    Double areaMayor = 0.0;
    for (AmbienteDTO ambiente : propiedadRepository.getPropiedad().getEnvironments()) {
      Double areaDeAmbiente = ambiente.getEnvironment_length()* ambiente.getEnvironment_width();
      if (ambienteMasGrande == null || areaDeAmbiente > areaMayor){
        ambienteMasGrande = ambiente;
        areaMayor = areaDeAmbiente;
      }
    }
    return ambienteMasGrande;
  }


  public ArrayList<AreaAmbienteResponseDTO> listarAreas() {
    ArrayList areas= new ArrayList<AreaAmbienteResponseDTO>();
    for (AmbienteDTO ambiente : propiedadRepository.getPropiedad().getEnvironments()){
      areas.add(new AreaAmbienteResponseDTO(ambiente.getEnvironment_name(),ambiente.getEnvironment_length()* ambiente.getEnvironment_width()));
    }
    return areas;
  }
}
